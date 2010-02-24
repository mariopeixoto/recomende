package br.recomende.model.searching.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.recomende.infra.util.SpringScope;
import br.recomende.model.document.Document;
import br.recomende.model.profile.Profile;
import br.recomende.model.repository.DocumentRepository;
import br.recomende.model.searching.engine.api.ContentBasedSearchEngine;

@Service
@Scope(SpringScope.PROTOTYPE)
public class DocumentSearcher implements ContentBasedSearchEngine {
	
	private DocumentRepository documentRepository;
	
	@Autowired
	public DocumentSearcher(DocumentRepository documentRepository) {
		this.documentRepository = documentRepository;
	}

	public List<Document> search(Profile profile) {
		Map<Document, Double> documentMap = new HashMap<Document, Double>();
		Map<String, Double> elements = profile.getElements();
		for (Entry<String, Double> element : elements.entrySet()) {
			List<ScoredDocument> scoredDocuments = this.documentRepository.search(element.getKey());
			for (ScoredDocument scoredDocument : scoredDocuments) {
				Double score = documentMap.get(scoredDocument.getDocument());
				if (score == null) {
					score = scoredDocument.getScore()*element.getValue();
				} else {
					score += scoredDocument.getScore()*element.getValue();
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
		for (ScoredDocument scoredDocument : documents.subList(0, 5)) {
			finalDocs.add(scoredDocument.getDocument());
		}
		return finalDocs;
	}
	
}
