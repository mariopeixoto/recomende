package br.recomende.model.recommender.factory;

import java.util.List;

import br.recomende.model.recommender.api.Recommendable;
import br.recomende.model.recommender.api.RecommendableList;
import br.recomende.model.recommender.impl.RecommendableArrayList;

public class RecommendableListFactory {
	
	public static RecommendableList copyList(List<Recommendable> list) {
		return new RecommendableArrayList(list);
	}
	
	public static RecommendableList createList() {
		return new RecommendableArrayList();
	}

}
