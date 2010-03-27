package br.ic.grow.retiblog.tagParser;

public class TapParserFactory {
	
	public static TagParser getTechnoratiTagParser(String tag){
		
		return new TechnoratiTagParser(tag);
		
	}
	
	public static TagParser getTechnoratiTagParser(){
		
		return new TechnoratiTagParser();
		
	}

}
