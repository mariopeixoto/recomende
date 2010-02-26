package br.recomende.model.profiling.engine;

import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.apache.lucene.analysis.fr.FrenchAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.recomende.infra.util.SpringScope;
import br.recomende.model.curriculum.Languages;

@Component
@Scope(SpringScope.APPLICATION)
public final class AnalyzerFactory {
	
	private final Map<Languages, Class<?>> CLASSES;

	public AnalyzerFactory() {
		CLASSES = new HashMap<Languages, Class<?>>();
		CLASSES.put(Languages.PT, BrazilianAnalyzer.class);
		CLASSES.put(Languages.FR, FrenchAnalyzer.class);
	}
	
	public Analyzer instanceFor(Languages language) {
		Class<?> analyzerClass = CLASSES.get(language);
		if (analyzerClass != null) {
			try {
				Object instance = analyzerClass.newInstance();
				return (Analyzer) instance;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return new StandardAnalyzer();
	}

}
