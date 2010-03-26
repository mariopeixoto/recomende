package br.recomende.model.recommender.api;


public interface IMiner<T> {
	
	T mine(Object... params) throws MineException;

}
