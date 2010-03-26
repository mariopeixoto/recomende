package br.recomende.model.recommender.api;

public class SearchException extends Exception {

	private static final long serialVersionUID = 7498204316105391822L;

	public SearchException() {
		super();
	}

	public SearchException(String message, Throwable cause) {
		super(message, cause);
	}

	public SearchException(String message) {
		super(message);
	}

	public SearchException(Throwable cause) {
		super(cause);
	}

}
