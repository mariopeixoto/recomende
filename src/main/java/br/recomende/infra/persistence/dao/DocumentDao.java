package br.recomende.infra.persistence.dao;

import java.util.Collection;
import java.util.List;

import org.apache.lucene.search.Query;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.recomende.model.document.Document;
import br.recomende.model.recommender.QueryGenerator;
import br.recomende.model.repository.DocumentRepository;
import br.recomende.model.searching.engine.DocumentSearchResultTransformer;
import br.recomende.model.searching.engine.ScoredDocument;

@Repository
public class DocumentDao extends RepositoryWrapper<Document, Integer>
		implements DocumentRepository {
	
	private QueryGenerator queryGenerator;
	
	@Autowired
	public DocumentDao(QueryGenerator queryGenerator) {
		super(Document.class);
		this.queryGenerator = queryGenerator;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ScoredDocument> search(String term) {
		FullTextSession fullTextSession = Search.getFullTextSession(super.getSession());
		Query luceneQuery = this.queryGenerator.generate(term);
		FullTextQuery query = fullTextSession.createFullTextQuery(luceneQuery, Document.class);
		query.setProjection(FullTextQuery.SCORE, FullTextQuery.THIS);
		query.setResultTransformer(new DocumentSearchResultTransformer());
		return (List<ScoredDocument>)query.list();
	}
	
	@Transactional(readOnly = false, propagation=Propagation.REQUIRES_NEW)
	protected void indexAll() {
		Collection<Document> documents = super.list();
		FullTextSession fullTextSession = Search.getFullTextSession(super.getSession());
		for (Document document : documents) {
			fullTextSession.purge(document.getClass(), document);
			fullTextSession.index(document);
		}
		fullTextSession.flushToIndexes();
	}

}
