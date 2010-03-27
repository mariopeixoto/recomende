package br.ic.grow.retriblog.preprocessing;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;

public class EnglishFiltering extends Preprocessing{
	
	public EnglishFiltering() {}

	public EnglishFiltering(String text) {
		super(text);
	}

	TokenStream analyzeToken() throws IOException {
		Analyzer analyzer = new SimpleAnalyzer();
		
		TokenStream stream = analyzer.tokenStream("contents", new StringReader(text));
		
		return stream;
}
	
	String analyzeString() throws IOException {
		Analyzer analyzer = new SimpleAnalyzer();
		String returnString = "";
		TokenStream stream = analyzer.tokenStream("contents", new StringReader(text));
		while (true) {
            Token token = stream.next();
            if (token == null) break;

            returnString +=  token.termText() + " ";
        }
		return returnString;
	}

}