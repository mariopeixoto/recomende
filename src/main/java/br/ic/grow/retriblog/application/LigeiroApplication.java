package br.ic.grow.retriblog.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.ic.grow.retiblog.index.MockIndexing;
import br.ic.grow.retriblog.persistence.HibernatePersistence;
import br.ic.grow.retriblog.persistence.ItemRepository;
import br.ic.grow.retriblog.preprocessing.Preprocessing;
import br.ic.grow.retriblog.preprocessing.PreprocessingControler;
import br.ic.grow.retriblog.preprocessing.PreprocessingFactory;
import br.ic.grow.retriblog.textExtraction.TextExtractionFactory;
import br.recomende.infra.util.SpringScope;
import br.recomende.model.harvester.blog.IBlogPostSubmitter;

@Service
@Scope(SpringScope.PROTOTYPE)
public class LigeiroApplication extends Application {
	
	private static final String[] tags = {"agile", "business", "case-study", "software-architecture",
			"domain-driven-design", "lop", "everyday-tales", "software-design",
			"groovy", "haskell", "java", "lisp", "management", "object-orientation",
			"ruby", "trends"};
	
	@Autowired
	public LigeiroApplication(ItemRepository itemRepository, IBlogPostSubmitter blogPostSubmitter) {
		super(tags.length, new ArrayList<String>(Arrays.asList(tags)), "en", PreprocessingFactory.getEnglishStemming(),
				new HibernatePersistence(itemRepository, blogPostSubmitter), new MockIndexing(),
						TextExtractionFactory.getSummaryStrategy(), null, null);
	}
	
	boolean checksLanguage() {
		return this.getLanguage().equals(this.getBlogLanguage());
	}

	String preprocesText() {
		PreprocessingControler preprocessingControler = new PreprocessingControler();
		Preprocessing p = this.getPreprocessing();
		p.setText(this.getText());
		try {
			return preprocessingControler.analyzeStringRun(p);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
