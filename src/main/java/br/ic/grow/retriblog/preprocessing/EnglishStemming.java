package br.ic.grow.retriblog.preprocessing;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.snowball.SnowballAnalyzer;
import java.io.StringReader;
import java.io.IOException;

public class EnglishStemming extends Preprocessing{

	public EnglishStemming() {}

	public EnglishStemming(String text) {
		super(text);
	}

	TokenStream analyzeToken() throws IOException {

		Analyzer analyzer = new SnowballAnalyzer("English", StopAnalyzer.ENGLISH_STOP_WORDS);

		TokenStream stream = analyzer.tokenStream("contents", new StringReader(text));

		return stream;
	}
	
	String analyzeString() throws IOException {

		Analyzer analyzer = new SnowballAnalyzer("English", StopAnalyzer.ENGLISH_STOP_WORDS);
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
