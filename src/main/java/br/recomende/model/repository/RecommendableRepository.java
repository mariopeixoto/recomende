package br.recomende.model.repository;

import org.apache.lucene.search.Query;

import br.recomende.model.recommender.api.Recommendable;
import br.recomende.model.recommender.api.RecommendableList;

public interface RecommendableRepository extends GenericRepository<Recommendable, Integer> {
	
	RecommendableList search(Query query, Class<?> documentClass);
	
	void reindex();
	
	<Type extends Recommendable> Recommendable get(Class<Type> clazz, Integer id);

}
