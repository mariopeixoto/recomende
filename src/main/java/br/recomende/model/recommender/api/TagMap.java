package br.recomende.model.recommender.api;

import java.io.Serializable;
import java.util.Map;

public interface TagMap<T extends Tag> extends Serializable, Cloneable,  Map<String, T>, Mergeable<TagMap<T>> {
	
}
