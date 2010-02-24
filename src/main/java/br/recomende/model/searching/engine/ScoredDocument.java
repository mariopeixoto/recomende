package br.recomende.model.searching.engine;

import br.recomende.model.document.Document;

public class ScoredDocument {
	
	private Document document;
	
	private Double score;
	
	public ScoredDocument() {
	}
	
	public ScoredDocument(Document document, Double score) {
		this.document = document;
		this.score = score;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

}
