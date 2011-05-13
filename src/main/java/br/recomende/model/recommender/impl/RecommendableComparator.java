package br.recomende.model.recommender.impl;

import java.util.Comparator;

import br.recomende.model.recommender.api.Recommendable;

public class RecommendableComparator implements Comparator<Recommendable> {

	@Override
	public int compare(Recommendable o1, Recommendable o2) {
		return o2.getScore().compareTo(o1.getScore());
	}

}
