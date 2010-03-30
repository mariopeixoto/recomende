package br.recomende.model.repository;

import java.util.List;

import br.recomende.model.document.Document;
import br.recomende.model.searching.engine.ScoredDocument;

public interface DocumentRepository extends GenericRepository<Document, Integer>{
	
	List<ScoredDocument> search(String term, Class<?> documentClass);
	
}
