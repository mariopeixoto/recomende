package br.recomende.model.recommender.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import br.recomende.model.recommender.api.Tag;
import br.recomende.model.recommender.api.TagMap;
import br.recomende.model.recommender.factory.TagFactory;

public class ConcurrentTagMap<T extends Tag> extends ConcurrentHashMap<String, T> implements TagMap<T>, Cloneable {
	
	private static final long serialVersionUID = 3018693451709969007L;
	
	public ConcurrentTagMap() {
		super();
	}
	
	public ConcurrentTagMap(Map<String, T> map) {
		super(map);
	}
	
	public void normalize(Double higherTerm) {
		for (Tag tag : this.values()) {
			tag.setWeight(tag.getWeight()/higherTerm);
		}
	}

	@Override
	public void merge(TagMap<T> set) {
		for(Entry<String,T> entry : set.entrySet()) {
			String key = entry.getKey();
			T value = entry.getValue();
			
			Tag o = this.get(key);
			if (o != null) {
				o.setWeight(o.getWeight() + value.getWeight());
			} else {
				this.put(key, value);
			}
		}
	}
	
	@Override
	public ConcurrentTagMap<Tag> clone() {
		ConcurrentTagMap<Tag> tags = new ConcurrentTagMap<Tag>();
		for (Tag tag: this.values()) {
			tags.put(tag.getTag(), TagFactory.createTag(tag.getTag(), tag.getWeight()));
		}
		return tags;
	}
	
}
