package br.recomende.model.profiling.engine;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.recomende.infra.util.SpringScope;
import br.recomende.model.curriculum.Languages;

@Service
@Scope(SpringScope.PROTOTYPE)
public class TermScorer {
	
	private Map<String, Double> termsMap;
	private AnalyzerFactory analyzerFactory;
	private static final Map<TermField, Double> TERM_MULTIPLIER;
	
	static {
		TERM_MULTIPLIER = new HashMap<TermField, Double>();
		TERM_MULTIPLIER.put(TermField.TITLE, 1.0);
		TERM_MULTIPLIER.put(TermField.KEYWORDS, 3.0);
		TERM_MULTIPLIER.put(TermField.TEXT, 1.0);
	}
	
	@Autowired
	public TermScorer(AnalyzerFactory analyzerFactory) {
		this.termsMap = new HashMap<String,Double>();
		this.analyzerFactory = analyzerFactory;
		
	}
	
	public void score(TermField field, Languages language, String text, Boolean relevant) throws IOException {
		Analyzer analyzer = this.analyzerFactory.instanceFor(language);
		TokenStream tokenStream = analyzer.tokenStream(field.getName(), new StringReader(text));
		
		Map<String, Integer> termsFrequency = new HashMap<String, Integer>();
		Token token = new Token();
		while((token = tokenStream.next(token)) != null) {
			String term = token.term();
			Integer tf = termsFrequency.get(term);
			if (tf != null) {
				termsFrequency.put(term, tf+1);
			} else {
				termsFrequency.put(term, 1);
			}
		}
		
		for (String term : termsFrequency.keySet()) {
			this.putTerm(term, termsFrequency.get(term), field, relevant);
		}
		
	}
	
	private void putTerm(String term, Integer frequency, TermField field, Boolean relevant) {
		Double termScore = this.termsMap.get(term);
		Double relevantMultiplier = relevant ? 2.0 : 1.0;
		if (termScore != null) {
			this.termsMap.put(term, termScore + (frequency*TERM_MULTIPLIER.get(field)*relevantMultiplier));
		} else {
			this.termsMap.put(term, frequency*TERM_MULTIPLIER.get(field)*relevantMultiplier);
		}
	}
	
	private void normalize() {
		Double higherTerm = null;
		for (Double value: this.termsMap.values()) {
			if (higherTerm != null) {
				if (value > higherTerm) {
					higherTerm = value;
				}
			} else {
				higherTerm = value;
			}
		}
		for (String term : this.termsMap.keySet()) {
			this.termsMap.put(term, this.termsMap.get(term)/higherTerm);
		}
	}
	
	public Map<String, Double> getScores() {
		this.normalize();
		return this.termsMap;
	}
	
	public void score(TermField field, Languages language, List<String> terms, Boolean relevant) throws IOException {
		StringBuilder builder = new StringBuilder();
		for (String term : terms) {
			builder.append(term).append(" ");
		}
		this.score(field, language, builder.toString(), relevant);
	}
	
}
