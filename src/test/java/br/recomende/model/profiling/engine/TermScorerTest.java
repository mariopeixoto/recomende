package br.recomende.model.profiling.engine;

import java.io.IOException;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.recomende.model.curriculum.Languages;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TermScorerTest {

	@Autowired
	private TermScorer termScorer;
	
	@Test
	public void testScoring() throws IOException {
		this.termScorer.score(TermField.TITLE, Languages.EN, "This paper presents a collection of " +
				"problems for natural language analysis derived mainly from theoretical linguistics.", true);
		this.termScorer.score(TermField.KEYWORDS, Languages.EN, "theoretical linguistics analysis", true);
		this.termScorer.score(TermField.TITLE, Languages.EN, "Botanical materia medica and pharmacology;" +
				" drugs considered from a botanical, pharmaceutical, physiological, therapeutical and toxicological standpoint.", false);
		this.termScorer.score(TermField.KEYWORDS, Languages.EN, "physiological botanical pharmacology", false);
		Map<String, Double> scores = this.termScorer.getScores();
		
		Assert.assertNotNull(scores);
	}
	
}
