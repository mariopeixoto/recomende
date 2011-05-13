package br.recomende.model.recommender.engine.mine;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.br.BrazilianStopWordAnalyzer;

import br.recomende.model.recommender.api.Tag;
import br.recomende.model.recommender.api.TagMap;
import br.recomende.model.recommender.api.annotation.BeginMethod;
import br.recomende.model.recommender.api.annotation.Miner;
import br.recomende.model.recommender.factory.TagFactory;
import br.recomende.model.recommender.impl.ConcurrentTagMap;

@Miner
public class TextMiner {
	
	private ConcurrentTagMap<Tag> generateMap(List<String> tokens) {
		ConcurrentTagMap<Tag> profile = new ConcurrentTagMap<Tag>();
		for (String term: tokens) {
			Tag tag = profile.get(term);
			if (tag != null) {
				tag.setWeight(tag.getWeight() + 1.0);
			} else {
				profile.put(term, TagFactory.createTag(term, 1.0));
			}
		}
		
		return profile;
	}
	
	private Double getHigherTerm(TagMap<Tag> profile) {
		Double higherTerm = null;
		for (Tag tag: profile.values()) {
			Double value = tag.getWeight();
			if (higherTerm != null) {
				if (value > higherTerm) {
					higherTerm = value;
				}
			} else {
				higherTerm = value;
			}
		}
		return higherTerm;
	} 
	
	public List<String> tokenize(String text) throws IOException {
		
		Analyzer analyzer = new BrazilianStopWordAnalyzer();
		TokenStream tokenStream = analyzer.tokenStream("contents", new StringReader(text));
		
		Token token = new Token();
		List<String> tokens = new ArrayList<String>();
		while((token = tokenStream.next(token)) != null) {
			String term = token.term();
			tokens.add(term);
		}
		
		return tokens;

	}
	
	@BeginMethod
	public ConcurrentTagMap<Tag> mine(String text) throws IOException {
		List<String> tokens = this.tokenize(text);
		ConcurrentTagMap<Tag> tagMap = this.generateMap(tokens);
		Double higherTerm = this.getHigherTerm(tagMap);
		tagMap.normalize(higherTerm);
		return tagMap;
	}  

}
