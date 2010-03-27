package br.ic.grow.retiblog.tagParser;


import java.util.ArrayList;

import br.ic.grow.retriblog.data.Item;

public abstract class TagParser {

	String tag;
	
	public TagParser() {}

	public TagParser(String tag) {
		super();
		this.tag = tag;
	}

	abstract ArrayList<Item> tagSearch();

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
	
}
