package br.ic.grow.retriblog.application;


import java.util.ArrayList;

import br.ic.grow.retiblog.index.Indexing;
import br.ic.grow.retriblog.persistence.Persistence;
import br.ic.grow.retriblog.preprocessing.Preprocessing;
import br.ic.grow.retriblog.textExtraction.TextExtraction;

public class ApplicationFactory {
	
	
	public static Application getTechnoratiApplication(int numberOfTags, ArrayList<String> tags,
			String language, Preprocessing preprocessing,
			Persistence persistence, Indexing indexing,
			TextExtraction textExtraction, String blogLanguage, String text){
			
		return new TechnoratiApplication(numberOfTags, tags, language, preprocessing, persistence, indexing,
				textExtraction, blogLanguage, text);
		
	}
	
	public static Application getTechnoratiApplication(){
		
		return new TechnoratiApplication();
		
	}
	
}
