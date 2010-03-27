package br.ic.grow.retriblog.textExtraction;

import java.util.ArrayList;

public abstract class TextExtraction {
	
	ArrayList<Object> parametros;
	
	TextExtraction() {}
	
	TextExtraction(ArrayList<Object> parametros) {
		this.parametros = parametros;
	}

	abstract String getText();

	public ArrayList<Object> getParametros() {
		return parametros;
	}

	public void setParametros(ArrayList<Object> parametros) {
		this.parametros = parametros;
	}
	
	

}
