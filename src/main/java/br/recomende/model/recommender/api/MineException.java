package br.recomende.model.recommender.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class MineException extends Exception {

	private static final long serialVersionUID = 3311538199548715286L;

	public MineException() {
		super();
	}

	public MineException(String message, Throwable cause) {
		super(message, cause);
	}

	public MineException(String message) {
		super(message);
	}

	public MineException(Throwable cause) {
		super(cause);
	}

}
