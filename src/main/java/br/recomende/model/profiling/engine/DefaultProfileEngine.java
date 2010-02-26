package br.recomende.model.profiling.engine;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.recomende.infra.util.SpringScope;
import br.recomende.model.curriculum.BibliographicProduction;
import br.recomende.model.curriculum.CurriculumVitae;
import br.recomende.model.curriculum.Languages;
import br.recomende.model.profile.Profile;
import br.recomende.model.profiling.engine.api.ProfileEngine;

@Service
@Scope(SpringScope.PROTOTYPE)
public class DefaultProfileEngine implements ProfileEngine {
	
	private TermScorer termScorer;
	private Logger log = LoggerFactory.getLogger(DefaultProfileEngine.class);
	
	@Autowired
	public DefaultProfileEngine(TermScorer termScorer) {
		this.termScorer = termScorer;
	}

	public Profile generate(CurriculumVitae curriculumVitae) {
		for (BibliographicProduction production : curriculumVitae.getBibliographicProductions()) {
			try {
				Languages titleLanguage = Languages.getType(production.getLanguage());
				if (titleLanguage == Languages.EN) {//Prototipo apenas com inglês
					termScorer.score(TermField.TITLE, titleLanguage, production.getTitle(), production.getRelevant());
					termScorer.score(TermField.KEYWORDS, titleLanguage, production.getKeywords(), production.getRelevant());
				}
			} catch (IOException e) {
				log.debug(e.getLocalizedMessage());
				continue;
			}
		}
		Map<String, Double> terms = termScorer.getScores();
		return new Profile(terms);
	}
	
}
