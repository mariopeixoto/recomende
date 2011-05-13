package br.recomende.model.recommender.impl;

import java.io.Serializable;

import br.recomende.model.recommender.api.Tag;

public class TagImpl implements Tag, Serializable {
	
	private static final long serialVersionUID = -6481056194003694079L;
	
	private String tag;
	
	private Double weight;
	
	public TagImpl(String tag, Double weight) {
		this.tag = tag;
		this.weight = weight;
	}
	public TagImpl(String tag) {
		this.tag = tag;
	}
	
	public String getTag() {
		return this.tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public Double getWeight() {
		return weight;
	}
	
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	@Override
	public String toString() {
		return weight.toString();
	}
	
}
