package br.recomende.model.recommender;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.BooleanClause.Occur;
import org.hibernate.search.annotations.Indexed;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.recomende.infra.util.SpringScope;
import br.recomende.model.document.Document;
import br.recomende.model.profile.Tag;
import br.recomende.model.profile.TagSet;

@Component
@Scope(SpringScope.APPLICATION)
public class QueryGenerator {
	
	private Reflections reflections;
	
	public QueryGenerator() {
		this.reflections = new Reflections("br", new TypeAnnotationsScanner() );
	}
	
	public Query generate(String term) {
		BooleanQuery query = new BooleanQuery();
		Set<Class<?>> classes = this.getIndexedClasses();
		for(Class<?> clazz : classes) {
			if(Document.class.isAssignableFrom(clazz)) {
				for(Field field : clazz.getDeclaredFields()) {
					if (field.isAnnotationPresent(org.hibernate.search.annotations.Field.class)) {
						Term fieldTerm = new Term(field.getName(), term);
						FuzzyQuery fuzzyQuery = new FuzzyQuery(fieldTerm);
						query.add(fuzzyQuery, BooleanClause.Occur.SHOULD);
					}
				}
			}
		}
		return query;
	}
	
	public Set<Class<?>> getIndexedClasses() {
		return this.reflections.getTypesAnnotatedWith(Indexed.class);
	}
	
	private List<String> getSearcheableFields(Class<?> documentClass, boolean searchAll) {
		List<String> fields = new ArrayList<String>();
		if (searchAll) {
			Set<Class<?>> classes = this.getIndexedClasses();
			for (Class<?> clazz : classes) {
				fields.addAll(this.getSearcheableFields(clazz, false));
			}
		} else {
			if(Document.class.isAssignableFrom(documentClass)) {
				for(Field field : documentClass.getDeclaredFields()) {
					if (field.isAnnotationPresent(org.hibernate.search.annotations.Field.class)) {
						fields.add(field.getName());
					}
				}
			}
		}
		return fields;
	}
	
	public List<String> getSearcheableFields(Class<?> documentClass) {
		if (documentClass.equals(Document.class)) {
			return this.getSearcheableFields(documentClass, true);
		} else {
			return this.getSearcheableFields(documentClass, false);
		}
	}
	
	private Query generate(String field, TagSet tags) {
		BooleanQuery query = new BooleanQuery();
		for (Tag tag : tags) {
			TermQuery termQuery = new TermQuery(new Term(field, tag.getTag()));
			query.add(termQuery, Occur.SHOULD);
		}
		return query;
	}
	
	public Query generate(TagSet tags, Class<?> documentClass) {
		List<String> fields = this.getSearcheableFields(documentClass);
		BooleanQuery query = new BooleanQuery();
		for (String field : fields) {
			Query fieldQuery = this.generate(field, tags);
			query.add(fieldQuery, Occur.SHOULD);
		}
		return query;
	}

}
