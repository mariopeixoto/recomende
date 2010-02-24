package br.recomende.infra.util;

public interface SpringScope {
	
	final String PROTOTYPE = "prototype"; 
	final String APPLICATION = "singleton";
	final String SESSION = "session";
	final String REQUEST = "request";
	final String GLOBAL_SESSION = "globalSession";
	
}
