package br.recomende.model.recommender;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.recomende.infra.user.User;
import br.recomende.infra.util.SpringScope;
import br.recomende.model.document.DocumentList;
import br.recomende.model.recommender.api.ISearcher;
import br.recomende.model.recommender.api.SearchException;
import br.recomende.model.recommender.api.annotation.Searcher;
import br.recomende.model.repository.RecommendationRepository;

@Service
@Scope(SpringScope.APPLICATION)
public class DocumentSearcher implements ISearcher<DocumentList> {
	
	private RecommendUtil recommendUtil;
	
	private RecommendationRepository recommendationRepository;
	
	private static Logger log = LoggerFactory.getLogger(DocumentSearcher.class);
	
	@Autowired
	public DocumentSearcher(RecommendUtil recommendUtil, RecommendationRepository recommendationRepository) {
		this.recommendUtil = recommendUtil;
		this.recommendationRepository = recommendationRepository;
	}
	
	public DocumentList search(User user, Integer quantity, Object... params) throws SearchException {
		log.info("Init document search for user " + user.getUsername());
		try {
			log.info("Searching");
			DocumentList documents = this.recommendUtil.invokeComponentMethod(Searcher.class, DocumentList.class, params);
			log.info("Documents founded: " + documents.size());
			log.info("Looking for documents already recommended");
			List<Recommendation> recommendations = this.recommendationRepository.getByUser(user);
			log.info("Recommendations founded: " + recommendations.size());
			for (Recommendation recommendation : recommendations) {
				int docIndex = documents.contains(recommendation.getDocumentId(), recommendation.getDocumentClass());
				if (docIndex != -1) {
					documents.remove(docIndex);
				}
			}
			if (documents.size() > quantity) {
				log.info("Croping results to " + quantity);
				documents = new DocumentList(documents.subList(0, quantity));
			}
			log.info("Registering these recommendations");
			this.recommendationRepository.putByUser(user, documents);
			log.info("Returning documents");
			return documents;
		} catch (Exception e) {
			throw new SearchException(e);
		}
	}
	
}
