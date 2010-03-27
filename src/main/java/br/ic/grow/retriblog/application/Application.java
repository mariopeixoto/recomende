package br.ic.grow.retriblog.application;


import java.util.ArrayList;

import br.ic.grow.retiblog.index.Indexing;
import br.ic.grow.retiblog.index.IndexingFactory;
import br.ic.grow.retriblog.persistence.Persistence;
import br.ic.grow.retriblog.persistence.PersistenceFactory;
import br.ic.grow.retriblog.preprocessing.Preprocessing;
import br.ic.grow.retriblog.preprocessing.PreprocessingFactory;
import br.ic.grow.retriblog.textExtraction.TextExtraction;
import br.ic.grow.retriblog.textExtraction.TextExtractionFactory;

public abstract class Application {// colocar para instanciar preprocessing, 
	//index, textExtraction, mais que uma aplica��o
	
//	public Application applicationType();
	
	private int numberOfTags;
	private ArrayList<String> tags;
	private String language;
	private Preprocessing preprocessing;
	private Persistence persistence;
	private Indexing indexing;
	private TextExtraction textExtraction;
	private String blogLanguage;
	private String text;
	
	

	public Application(int numberOfTags, ArrayList<String> tags,
			String language, Preprocessing preprocessing,
			Persistence persistence, Indexing indexing,
			TextExtraction textExtraction, String blogLanguage, String text) {
		this.numberOfTags = numberOfTags;
		this.tags = tags;
		this.language = language;
		this.preprocessing = preprocessing;
		this.persistence = persistence;
		this.indexing = indexing;
		this.textExtraction = textExtraction;
		this.blogLanguage = blogLanguage;
		this.text = text;
	}
	
	public Application() {}

	abstract boolean checksLanguage();
	
	abstract String preprocesText();

	public int getNumberOfTags() {
		return numberOfTags;
	}

	public void setNumberOfTags(int numberOfTags) {
		this.numberOfTags = numberOfTags;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Preprocessing getPreprocessing() {
		return preprocessing;
	}

	public void setPreprocessing(Preprocessing preprocessing) {
		this.preprocessing = preprocessing;
	}

	public Persistence getPersistence() {
		return persistence;
	}

	public void setPersistence(Persistence persistence) {
		this.persistence = persistence;
	}

	public Indexing getIndexing() {
		return indexing;
	}

	public void setIndexing(Indexing indexing) {
		this.indexing = indexing;
	}

	public TextExtraction getTextExtraction() {
		return textExtraction;
	}

	public void setTextExtraction(TextExtraction textExtraction) {
		this.textExtraction = textExtraction;
	}

	public String getBlogLanguage() {
		return blogLanguage;
	}

	public void setBlogLanguage(String blogLanguage) {
		this.blogLanguage = blogLanguage;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	

}
