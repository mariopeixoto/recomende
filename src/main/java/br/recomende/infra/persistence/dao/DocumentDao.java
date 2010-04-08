package br.recomende.infra.persistence.dao;

import java.util.Collection;
import java.util.List;

import org.apache.lucene.search.Query;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.stereotype.Repository;

import br.recomende.model.document.Document;
import br.recomende.model.repository.DocumentRepository;
import br.recomende.model.searching.engine.DocumentSearchResultTransformer;
import br.recomende.model.searching.engine.ScoredDocument;

@Repository
public class DocumentDao extends RepositoryWrapper<Document, Integer>
		implements DocumentRepository {
	
	public DocumentDao() {
		super(Document.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ScoredDocument> search(Query query, Class<?> documentClass) {
		FullTextSession fullTextSession = Search.getFullTextSession(super.getSession());
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(query, documentClass);
		fullTextQuery.setProjection(FullTextQuery.SCORE, FullTextQuery.THIS);
		fullTextQuery.setResultTransformer(new DocumentSearchResultTransformer());
		return (List<ScoredDocument>)fullTextQuery.list();
	}

	@Override
	public void reindex() {
		FullTextSession session = Search.getFullTextSession(super.getSession());
		Collection<Document> list = this.list();
		for (Document document : list) {
			session.index(document);
		}
		session.flushToIndexes();
	}
	
}
