package br.recomende.model.recommender.api.annotation;

public interface Mergeable<T> {

	void merge(T obj);
	
}
