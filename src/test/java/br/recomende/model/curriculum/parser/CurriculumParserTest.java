package br.recomende.model.curriculum.parser;

import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;

import org.junit.Test;
import org.xml.sax.SAXException;

import br.recomende.model.curriculum.CurriculumVitae;

public class CurriculumParserTest {
	
	private final String curriculumPath = "/curriculum/test.xml";
	
	@Test
	public void parse() throws IOException, SAXException {
		CurriculumParser parser = new CurriculumParser();
		InputStream fileStream = CurriculumParserTest.class.getResourceAsStream(curriculumPath);
		CurriculumVitae curriculumVitae = parser.parse(fileStream);
		
		Assert.assertNotNull(curriculumVitae);
		Assert.assertNotNull(curriculumVitae.getAtualizationDate());
		Assert.assertNotNull(curriculumVitae.getSummary());
		Assert.assertNotNull(curriculumVitae.getOtherRelevantInformation());
		Assert.assertTrue(curriculumVitae.getScienceAreas().size() > 0);
		Assert.assertTrue(curriculumVitae.getBibliographicProductions().size() > 0);
		Assert.assertTrue(curriculumVitae.getTecnicalProductions().size() > 0);
		Assert.assertTrue(curriculumVitae.getLanguages().size() > 0);
	}
	
}
