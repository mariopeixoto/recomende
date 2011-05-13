package br.recomende.model.recommender.api;

import java.util.List;

public interface RecommendableList extends List<Recommendable>, Mergeable<RecommendableList> {
	
	int contains(Integer id, Class<?> documentClass);

}
