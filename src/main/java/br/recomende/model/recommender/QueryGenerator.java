package br.recomende.model.recommender;

import java.lang.reflect.Field;
import java.util.Set;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.Query;
import org.hibernate.search.annotations.Indexed;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.recomende.infra.util.SpringScope;
import br.recomende.model.document.Document;

@Component
@Scope(SpringScope.APPLICATION)
public class QueryGenerator {
	
	private Reflections reflections;
	
	private static Logger log = LoggerFactory.getLogger(QueryGenerator.class); 

	public QueryGenerator() {
		this.reflections = new Reflections("br.recomende", new TypeAnnotationsScanner() );
	}
	
	public Query generate(String term) {
		BooleanQuery query = new BooleanQuery();
		Set<Class<?>> classes = this.reflections.getTypesAnnotatedWith(Indexed.class);
		for(Class<?> clazz : classes) {
			log.info("Indexed Class: " + clazz.getCanonicalName());
			if(Document.class.isAssignableFrom(clazz)) {
				for(Field field : clazz.getDeclaredFields()) {
					if (field.isAnnotationPresent(org.hibernate.search.annotations.Field.class)) {
						log.info("Indexed Field: " + field.getName());
						Term fieldTerm = new Term(field.getName(), term);
						FuzzyQuery fuzzyQuery = new FuzzyQuery(fieldTerm);
						query.add(fuzzyQuery, BooleanClause.Occur.SHOULD);
					}
				}
			}
		}
		return query;
	}

}
