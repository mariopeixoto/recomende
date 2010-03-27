package br.ic.grow.retriblog.application;


import java.io.IOException;
import java.util.ArrayList;

import br.ic.grow.retiblog.index.Indexing;
import br.ic.grow.retiblog.index.IndexingFactory;
import br.ic.grow.retriblog.data.Item;
import br.ic.grow.retriblog.persistence.Persistence;
import br.ic.grow.retriblog.persistence.PersistenceFactory;
import br.ic.grow.retriblog.preprocessing.Preprocessing;
import br.ic.grow.retriblog.preprocessing.PreprocessingControler;
import br.ic.grow.retriblog.preprocessing.PreprocessingFactory;
import br.ic.grow.retriblog.textExtraction.TextExtraction;
import br.ic.grow.retriblog.textExtraction.TextExtractionFactory;

public class TechnoratiApplication extends Application{

	
	public TechnoratiApplication() {
		super();
	}


	public TechnoratiApplication(int numberOfTags, ArrayList<String> tags,
			String language, Preprocessing preprocessing,
			Persistence persistence, Indexing indexing,
			TextExtraction textExtraction, String blogLanguage, String text) {
		super(numberOfTags, tags, language, preprocessing, persistence, indexing,
				textExtraction, blogLanguage, text);
	}



	public int getNumberOfTags(){
		return 5;
	}
	
	public ArrayList<String> getTags(){
		ArrayList<String> tags = new ArrayList<String>();
		
		tags.add("sports");
		tags.add("sports");
		
		return tags;
	}
	
	public String getLanguage(){
		return "en";
	}
	
	public Preprocessing getPreprocessing(){
		return PreprocessingFactory.getEnglishStemming();
	}
	
	public Indexing getIndexing(){
		return IndexingFactory.getLuceneIndexing();
	}
	
	public Persistence getPersistence(){
		return PersistenceFactory.getMySqlPersistence();
	}
	
	public TextExtraction getTextExtraction(){
		return TextExtractionFactory.getSummaryStrategy();
	}

	boolean checksLanguage(){
		return this.getBlogLanguage().equals(this.getLanguage());
	}
	
	String preprocesText(){
		
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
