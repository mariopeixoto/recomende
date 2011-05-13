package br.recomende.model.recommender.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import br.recomende.model.recommender.api.Recommendable;
import br.recomende.model.recommender.api.RecommendableList;

public class RecommendableArrayList extends ArrayList<Recommendable> implements RecommendableList {
	
	private static final long serialVersionUID = 8235698547155469691L;
	
	public RecommendableArrayList() {
		super();
	}

	public RecommendableArrayList(Collection<? extends Recommendable> c) {
		super(c);
		Collections.sort(this, new RecommendableComparator());
	}

	public RecommendableArrayList(int initialCapacity) {
		super(initialCapacity);
	}
	
	public int contains(Integer id, Class<?> documentClass) {
		for (Recommendable recommendable : this) {
			if (recommendable.getId().equals(id) && recommendable.getClass().equals(documentClass)) {
				return this.indexOf(recommendable);
			}
		}
		return -1;
	}

	@Override
	public void merge(RecommendableList list) {
		for (Recommendable recommendable : list) {
			int index;
			if ((index = this.contains(recommendable.getId(), recommendable.getClass())) != -1) {
				Recommendable alreadyOnList = this.get(index);
				if (alreadyOnList.getScore() < recommendable.getScore()) {
					this.set(index, recommendable);
				}
			} else {
				this.add(recommendable);
			}
		}
		Collections.sort(this, new RecommendableComparator());
	}

}
