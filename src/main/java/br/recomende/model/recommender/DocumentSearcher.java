package br.recomende.model.recommender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.recomende.infra.util.SpringScope;
import br.recomende.model.document.DocumentList;
import br.recomende.model.recommender.api.ISearcher;
import br.recomende.model.recommender.api.SearchException;
import br.recomende.model.recommender.api.annotation.Searcher;

@Service
@Scope(SpringScope.APPLICATION)
public class DocumentSearcher implements ISearcher<DocumentList> {
	
	private RecommendUtil recommendUtil;
	
	@Autowired
	public DocumentSearcher(RecommendUtil recommendUtil) {
		this.recommendUtil = recommendUtil;
	}
	
	public DocumentList search(Object... params) throws SearchException {
		try {
			return this.recommendUtil.invokeComponentMethod(Searcher.class, DocumentList.class, params);
		} catch (Exception e) {
			throw new SearchException(e);
		}
	}
	
}
