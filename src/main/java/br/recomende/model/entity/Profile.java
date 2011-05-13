package br.recomende.model.entity;

import java.util.HashMap;
import java.util.Map;

import br.recomende.model.recommender.api.Tag;
import br.recomende.model.recommender.factory.TagFactory;
import br.recomende.model.recommender.impl.ConcurrentTagMap;

public class Profile extends ConcurrentTagMap<Tag> {
	
	private static final long serialVersionUID = 3018693451709969007L;
	
	public Profile() {
		super();
	}
	
	public Profile(Map<String, Double> tags) { 
		this();
		for (Entry<String, Double> entry : tags.entrySet()) {
			Tag tag = TagFactory.createTag(entry.getKey(), entry.getValue());
			super.put(entry.getKey(), tag);
		}
	}
	
	public Map<String, Double> getMap() {
		Map<String, Double> map = new HashMap<String, Double>();
		for (Entry<String, Tag> entry : super.entrySet()) {
			Tag value = entry.getValue();
			map.put(entry.getKey(), value == null ? null : value.getWeight());
		}
		return map;
	} 
	
}
