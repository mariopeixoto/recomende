package br.recomende.model.profiling.engine;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import br.recomende.model.curriculum.Languages;
import br.recomende.model.profile.TagSet;
import br.recomende.model.recommender.api.annotation.BeginMethod;
import br.recomende.model.recommender.api.annotation.Miner;

@Miner
public class TextDataMiner {
	
	private TermScorer termScorer;
	
	@Autowired
	public TextDataMiner(TermScorer termScorer) {
		this.termScorer = termScorer;
	}

	@BeginMethod
	public TagSet mine(String text) throws IOException {
		termScorer.score(TermField.TEXT, Languages.EN, text, false);
		Map<String, Double> terms = termScorer.getScores();
		return new TagSet(terms);
	}
	
}
