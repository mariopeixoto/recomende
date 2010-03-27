package br.ic.grow.retriblog.application;


import java.io.IOException;
import java.util.ArrayList;

import br.ic.grow.retiblog.index.Indexing;
import br.ic.grow.retiblog.index.IndexingFactory;
import br.ic.grow.retriblog.persistence.Persistence;
import br.ic.grow.retriblog.persistence.PersistenceFactory;
import br.ic.grow.retriblog.preprocessing.Preprocessing;
import br.ic.grow.retriblog.preprocessing.PreprocessingControler;
import br.ic.grow.retriblog.preprocessing.PreprocessingFactory;
import br.ic.grow.retriblog.textExtraction.TextExtraction;
import br.ic.grow.retriblog.textExtraction.TextExtractionFactory;

public class ApplicationControler {
	
	public int getNumberOfTagsRun(Application application){
		return application.getNumberOfTags();
	}
	
	public ArrayList<String> getTagsRun(Application application){
		return application.getTags();
	}
	
	public String getLanguageRun(Application application){
		return application.getLanguage();
	}
	
	public Preprocessing getPreprocessingRun(Application application){
		return application.getPreprocessing();
	}
	
	public Indexing getIndexingRun(Application application){
		return application.getIndexing();
	}
	
	public Persistence getPersistenceRun(Application application){
		return application.getPersistence();
	}
	
	public TextExtraction getTextExtractionRun(Application application){
		return application.getTextExtraction();
	}

	public boolean checksLanguageRun(Application application){
		return application.checksLanguage();
	}
	
	public String preprocesTextRun(Application application){
		return application.preprocesText();
	}

}
