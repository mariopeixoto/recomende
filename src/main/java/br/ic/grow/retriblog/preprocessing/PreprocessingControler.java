package br.ic.grow.retriblog.preprocessing;

import java.io.IOException;

import org.apache.lucene.analysis.TokenStream;

public class PreprocessingControler {
	
	public String analyzeStringRun(Preprocessing preprocessing) throws IOException{
		return preprocessing.analyzeString();
	}

	public TokenStream analyzeTokenRun(Preprocessing preprocessing) throws IOException{
		return preprocessing.analyzeToken();
	}
	
}
