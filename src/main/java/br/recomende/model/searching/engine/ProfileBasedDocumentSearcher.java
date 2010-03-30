package br.recomende.model.searching.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import br.recomende.model.document.Document;
import br.recomende.model.document.DocumentList;
import br.recomende.model.profile.Tag;
import br.recomende.model.profile.TagSet;
import br.recomende.model.recommender.api.annotation.BeginMethod;
import br.recomende.model.recommender.api.annotation.Searcher;
import br.recomende.model.repository.DocumentRepository;

@Searcher
public class ProfileBasedDocumentSearcher {
	
	private DocumentRepository documentRepository;
	
	@Autowired
	public ProfileBasedDocumentSearcher(DocumentRepository documentRepository) {
		this.documentRepository = documentRepository;
	}
	
	@BeginMethod
	public DocumentList search(TagSet tagSet, Class<?> documentClass) {
		TagSet tags = (TagSet) tagSet.clone();
		tags.crop(0.5);
		Map<Document, Double> documentMap = new HashMap<Document, Double>();
		for (Tag element : tags) {
			List<ScoredDocument> scoredDocuments = this.documentRepository.search(element.getTag(), documentClass);
			for (ScoredDocument scoredDocument : scoredDocuments) {
				Double score = documentMap.get(scoredDocument.getDocument());
				if (score == null) {
					score = scoredDocument.getScore()*element.getWeight();
				} else {
					score += scoredDocument.getScore()*element.getWeight();
				}
				documentMap.put(scoredDocument.getDocument(), score);
			}
		}
		List<ScoredDocument> documents = new ArrayList<ScoredDocument>();
		for (Entry<Document,Double> element : documentMap.entrySet()) {
			documents.add(new ScoredDocument(element.getKey(), element.getValue()));
		}
		Collections.sort(documents, new ScoredDocumentComparator());
		List<Document> finalDocs = new ArrayList<Document>();
		for (ScoredDocument scoredDocument : documents) {
			finalDocs.add(scoredDocument.getDocument());
		}
		return new DocumentList(finalDocs);
	}
	
}
