package br.recomende.infra.persistence.dao;

import java.util.Collection;

import org.apache.lucene.search.Query;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.recomende.infra.util.SpringScope;
import br.recomende.model.recommender.api.Recommendable;
import br.recomende.model.recommender.api.RecommendableList;
import br.recomende.model.recommender.factory.RecommendableListFactory;
import br.recomende.model.recommender.impl.RecommendableSearchResultTransformer;
import br.recomende.model.repository.RecommendableRepository;

@Component
@Scope(SpringScope.PROTOTYPE)
public class RecommendableDao extends RepositoryWrapper<Recommendable, Integer>
		implements RecommendableRepository{
	
	@Override
	public RecommendableList search(Query query, Class<?> documentClass) {
		FullTextSession fullTextSession = Search.getFullTextSession(super.getSession());
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(query, documentClass);
		fullTextQuery.setProjection(FullTextQuery.SCORE, FullTextQuery.THIS);
		fullTextQuery.setResultTransformer(new RecommendableSearchResultTransformer());
		return RecommendableListFactory.copyList(fullTextQuery.list());
	}

	@Override
	public void reindex() {
		FullTextSession session = Search.getFullTextSession(super.getSession());
		Collection<Recommendable> list = this.list();
		for (Recommendable document : list) {
			session.index(document);
		}
		session.flushToIndexes();
	}

	@Override
	public <Type extends Recommendable> Recommendable get(Class<Type> clazz, Integer id) {
		try {
			return (Recommendable) getSession().get(clazz, id);
		} catch (ObjectNotFoundException e) {
			return null;
		}
	}

}
