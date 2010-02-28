package br.recomende.model.searching.engine;

import java.util.Comparator;

public class ScoredDocumentComparator implements Comparator<ScoredDocument> {

	@Override
	public int compare(ScoredDocument o1, ScoredDocument o2) {
		int diff = o1.getScore().compareTo(o2.getScore()); 
		if (diff == 0) {
			diff = o1.getDocument().getId().compareTo(o2.getDocument().getId());
		}
		return diff;
	}

}
