package br.recomende.model.recommender.impl;

import java.util.List;

import org.hibernate.search.FullTextQuery;
import org.hibernate.transform.ResultTransformer;

import br.recomende.model.recommender.api.Recommendable;

public class RecommendableSearchResultTransformer implements ResultTransformer{

	private static final long serialVersionUID = -2511515981098967494L;
	
	@SuppressWarnings("rawtypes")
	@Override
	public List transformList(List list) {
		return list;
	}

	@Override
	public Object transformTuple(Object[] values, String[] params) {
		Recommendable recommendable = null;
		Double score = null;
		for(int i = 0;i < params.length;i++) {
			String param = params[i];
			if (FullTextQuery.THIS.equals(param)) {
				recommendable = ((Recommendable)values[i]);
			} else if (FullTextQuery.SCORE.equals(param)) {
				score = ((Float)values[i]).doubleValue();
			}
		}
		recommendable.setScore(score);
		return recommendable;
	}

}
