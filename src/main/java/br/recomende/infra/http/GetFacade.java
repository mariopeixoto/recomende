package br.recomende.infra.http;

import java.io.InputStream;
import java.util.Map;

public interface GetFacade {

	InputStream get(String endPoint, Map<String,Object> parameters);
	
}
