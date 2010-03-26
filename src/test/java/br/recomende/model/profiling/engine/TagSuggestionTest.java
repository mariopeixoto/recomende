package br.recomende.model.profiling.engine;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.recomende.model.curriculum.BibliographicProduction;
import br.recomende.model.curriculum.CurriculumVitae;
import br.recomende.model.curriculum.Language;
import br.recomende.model.curriculum.LanguageSkill;
import br.recomende.model.curriculum.Languages;
import br.recomende.model.profile.TagSet;
import br.recomende.model.recommender.TagSetMiner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TagSuggestionTest {
	
	@Autowired
	private TagSetMiner tagSuggestion;
	
	@Test
	public void suggest() throws Exception {
		CurriculumVitae curriculumVitae = new CurriculumVitae();
		BibliographicProduction bibliographicProduction = new BibliographicProduction();
		bibliographicProduction.setTitle("improved likelihood inference for the roughness parameter of the ga0 distribution");
		bibliographicProduction.addKeyword("speckle");
		bibliographicProduction.addKeyword("images");
		bibliographicProduction.addKeyword("image proccess");
		bibliographicProduction.setYear(2009);
		bibliographicProduction.setLanguage("inglês");
		bibliographicProduction.setRelevant(true);
		curriculumVitae.addBibliographicProduction(bibliographicProduction);
		Language english = new Language();
		english.setType(Languages.EN);
		english.setReadSkill(LanguageSkill.GOOD);
		curriculumVitae.addLanguage(english);
		TagSet profile = tagSuggestion.mine(curriculumVitae);
		Assert.assertNotNull(profile);
		TagSet text = tagSuggestion.mine("improved likelihood inference for the roughness parameter of the ga0 distribution");
		Assert.assertNotNull(text);
	}
	
}
