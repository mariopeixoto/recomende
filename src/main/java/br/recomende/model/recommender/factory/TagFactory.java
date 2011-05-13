package br.recomende.model.recommender.factory;

import br.recomende.model.recommender.api.Tag;
import br.recomende.model.recommender.impl.TagImpl;

public class TagFactory {
	
	public static Tag createTag(String tag, Double weight) {
		return new TagImpl(tag, weight);
	}

}
