package br.recomende.model.recommender.api;



public interface ISearcher<T> {
	
	T search(Object... params) throws SearchException;

}
