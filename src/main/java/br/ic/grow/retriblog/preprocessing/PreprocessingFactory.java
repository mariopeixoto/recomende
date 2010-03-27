package br.ic.grow.retriblog.preprocessing;

public class PreprocessingFactory {
	
	public static Preprocessing getCleanHTML(){
		return new CleanHTML();
	}
	
	public static Preprocessing getCleanHTML(String text){
		return new CleanHTML(text);
	}
	
		
	public static Preprocessing getEnglishFiltering(){
		return new EnglishFiltering();
	}
	
	public static Preprocessing getEnglishFiltering(String text){
		return new EnglishFiltering(text);
	}
	
	public static Preprocessing getEnglishStemming(){
		return new EnglishStemming();
	}
	
	public static Preprocessing getEnglishStemming(String text){
		return new EnglishStemming(text);
	}
	
	public static Preprocessing getWhiteSpaceAnalyser(){
		return new WhiteSpaceAnalyser();
	}

	public static Preprocessing getWhiteSpaceAnalyser(String text){
		return new WhiteSpaceAnalyser(text);
	}
}
