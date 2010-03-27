package br.ic.grow.retriblog.application;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.ic.grow.retiblog.index.IndexingFactory;
import br.ic.grow.retriblog.persistence.HibernatePersistence;
import br.ic.grow.retriblog.persistence.ItemRepository;
import br.ic.grow.retriblog.preprocessing.Preprocessing;
import br.ic.grow.retriblog.preprocessing.PreprocessingControler;
import br.ic.grow.retriblog.preprocessing.PreprocessingFactory;
import br.ic.grow.retriblog.textExtraction.TextExtractionFactory;
import br.recomende.infra.util.SpringScope;

@Service
@Scope(SpringScope.APPLICATION)
public class LigeiroApplication extends Application {
	
	private static ArrayList<String> tags;
	
	static {
		tags = new ArrayList<String>();
		tags.add("text");
	}
	
	@Autowired
	public LigeiroApplication(ItemRepository itemRepository) {
		super(5, new ArrayList<String>(tags), "en", PreprocessingFactory.getEnglishStemming(),
				new HibernatePersistence(itemRepository), IndexingFactory.getLuceneIndexing(/*TÁ INDEXANDO O QUE AQUI?!?!*/),
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
