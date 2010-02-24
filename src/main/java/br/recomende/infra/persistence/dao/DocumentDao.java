package br.recomende.infra.persistence.dao;

import java.util.List;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.TermQuery;
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
	public List<ScoredDocument> search(String term) {
		FullTextSession fullTextSession = Search.getFullTextSession(super.getSession());
		Term titleTerm = new Term("title", term);
		Term descriptionTerm = new Term("description", term);
		TermQuery titleQuery = new TermQuery(titleTerm);
		TermQuery descriptionQuery = new TermQuery(descriptionTerm);
		BooleanQuery luceneQuery = new BooleanQuery();
		luceneQuery.add(titleQuery, BooleanClause.Occur.SHOULD);
		luceneQuery.add(descriptionQuery, BooleanClause.Occur.SHOULD);
		FullTextQuery query = fullTextSession.createFullTextQuery(luceneQuery, Document.class);
		query.setProjection(FullTextQuery.SCORE, FullTextQuery.THIS);
		query.setResultTransformer(new DocumentSearchResultTransformer());
		return (List<ScoredDocument>)query.list();
	}

}
