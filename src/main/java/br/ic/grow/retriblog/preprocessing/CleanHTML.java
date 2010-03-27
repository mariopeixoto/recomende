package br.ic.grow.retriblog.preprocessing;

import java.io.IOException;

public class CleanHTML extends Preprocessing{
	
	public CleanHTML() {}
	
	public CleanHTML(String text) {
		super(text);
	}

	String analyzeString() throws IOException{
		String noHTMLString;
		noHTMLString = text.replaceAll("<[^>]*>", "");
		noHTMLString = noHTMLString.replaceAll("&.*?;", "");

		return noHTMLString;
	}
	
	

}
