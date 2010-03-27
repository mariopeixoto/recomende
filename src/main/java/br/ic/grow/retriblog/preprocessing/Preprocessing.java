package br.ic.grow.retriblog.preprocessing;


import java.io.IOException;

import org.apache.lucene.analysis.TokenStream;

public abstract class Preprocessing  {
	
	String text;
		
	public Preprocessing(String text) {
		this.text = text;
	}
	
	public Preprocessing() {}

	TokenStream analyzeToken() throws IOException{
		return null;
	}
	
	abstract String analyzeString() throws IOException;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	

}
