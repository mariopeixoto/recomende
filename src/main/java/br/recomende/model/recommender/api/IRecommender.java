package br.recomende.model.recommender.api;

import br.recomende.infra.user.User;

public interface IRecommender {
	
	RecommendableList recommend(User user, Integer quantity, Object... params) throws SearchException;

	RecommendableList recommend(Object... params) throws SearchException;
	
}
