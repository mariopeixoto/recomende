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
		log.info("Values Size: " + values.length);
		log.info("Params Size: " + params.length);
		ScoredDocument scoredDocument = new ScoredDocument();
		for(int i = 0;i < params.length;i++) {
			String param = params[i];
			log.info("Param: " + param);
			if (FullTextQuery.THIS.equals(param)) {
				log.info("Document Id: " + ((Document)values[i]).getId());
				scoredDocument.setDocument((Document)values[i]);
			} else if (FullTextQuery.SCORE.equals(param)) {
				log.info("Document Score: " + ((Float)values[i]).doubleValue());
				scoredDocument.setScore(((Float)values[i]).doubleValue());
			}
		}
		return scoredDocument;
	}

}
