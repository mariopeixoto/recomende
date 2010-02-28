package br.recomende.infra.exception.http;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenResourceException extends RuntimeException {

	private static final long serialVersionUID = 9171085188206763368L;

}
