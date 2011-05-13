package br.recomende.model.recommender.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.hibernate.search.annotations.Indexed;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.recomende.infra.util.SpringScope;
import br.recomende.model.recommender.api.Recommendable;
import br.recomende.model.recommender.api.Tag;
import br.recomende.model.recommender.api.TagMap;

@Component
@Scope(SpringScope.APPLICATION)
public class QueryGenerator {
	
	@Value("#{indexPackage}")
	private String indexPackage;
	
	private Reflections reflections;
	
	@PostConstruct
	public void create() {
		this.reflections = new Reflections(indexPackage, new TypeAnnotationsScanner() );
	}
	
	private Set<Class<?>> getIndexedClasses() {
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
			if(Recommendable.class.isAssignableFrom(documentClass)) {
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
		if (documentClass.equals(Recommendable.class)) {
			return this.getSearcheableFields(documentClass, true);
		} else {
			return this.getSearcheableFields(documentClass, false);
		}
	}
	
	private Query generate(String field, TagMap<Tag> tags) {
		BooleanQuery query = new BooleanQuery();
		for (Tag tag : tags.values()) {
			TermQuery termQuery = new TermQuery(new Term(field, tag.getTag()));
			termQuery.setBoost(tag.getWeight().floatValue());
			query.add(termQuery, Occur.SHOULD);
		}
		return query;
	}
	
	public Query generate(TagMap<Tag> tags, Class<?> documentClass) {
		List<String> fields = this.getSearcheableFields(documentClass);
		BooleanQuery query = new BooleanQuery();
		for (String field : fields) {
			Query fieldQuery = this.generate(field, tags);
			query.add(fieldQuery, Occur.SHOULD);
		}
		return query;
	}

}
