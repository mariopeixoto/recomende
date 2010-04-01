package br.recomende.model.searching.engine;

import java.util.List;

import org.hibernate.search.FullTextQuery;
import org.hibernate.transform.ResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.recomende.model.document.Document;

public class DocumentSearchResultTransformer implements ResultTransformer{

	private static final long serialVersionUID = -2511515981098967494L;
	
	private static Logger log = LoggerFactory.getLogger(DocumentSearchResultTransformer.class);

	@Override
	public List transformList(List list) {
		return list;
	}

	@Override
	public Object transformTuple(Object[] values, String[] params) {
		ScoredDocument scoredDocument = new ScoredDocument();
		for(int i = 0;i < params.length;i++) {
			String param = params[i];
			if (FullTextQuery.THIS.equals(param)) {
				scoredDocument.setDocument((Document)values[i]);
			} else if (FullTextQuery.SCORE.equals(param)) {
				scoredDocument.setScore(((Float)values[i]).doubleValue());
			}
		}
		return scoredDocument;
	}

}
