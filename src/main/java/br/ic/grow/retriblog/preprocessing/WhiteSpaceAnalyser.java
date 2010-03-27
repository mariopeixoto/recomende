package br.ic.grow.retriblog.preprocessing;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;

public class WhiteSpaceAnalyser extends Preprocessing{
	
	public WhiteSpaceAnalyser() {}
	
	public WhiteSpaceAnalyser(String text) {
		super(text);
	}

	TokenStream analyzeToken() throws IOException {
		Analyzer analyzer = new WhitespaceAnalyzer();
		
		TokenStream stream = analyzer.tokenStream("contents", new StringReader(text));
		
		return stream;
}
	
	String analyzeString() throws IOException {
		Analyzer analyzer = new WhitespaceAnalyzer();
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