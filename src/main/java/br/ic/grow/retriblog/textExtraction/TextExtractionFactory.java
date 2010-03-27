package br.ic.grow.retriblog.textExtraction;

import java.util.ArrayList;


public class TextExtractionFactory {
	


	public static TextExtraction getSummaryStrategy(){
		return new SummaryStrategy();
	}
	
	public static TextExtraction getSummaryStrategy(ArrayList<Object> parametros){
		return new SummaryStrategy(parametros);
	}
}
