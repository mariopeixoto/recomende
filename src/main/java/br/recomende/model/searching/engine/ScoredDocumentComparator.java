package br.recomende.model.searching.engine;

import java.util.Comparator;

public class ScoredDocumentComparator implements Comparator<ScoredDocument> {

	@Override
	public int compare(ScoredDocument o1, ScoredDocument o2) {
		return o1.getScore().compareTo(o2.getScore());
	}

}
