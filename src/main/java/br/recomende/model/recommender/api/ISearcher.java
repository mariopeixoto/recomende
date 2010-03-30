package br.recomende.model.recommender.api;

import br.recomende.infra.user.User;



public interface ISearcher<T> {
	
	T search(User user, Integer quantity, Object... params) throws SearchException;

}
