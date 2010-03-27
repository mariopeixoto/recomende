package br.ic.grow.retiblog.index;

import java.io.IOException;

public abstract class Indexing {

	String indexDir, keyword, unIndexed, unStored, text;
	
	
	
	public Indexing() {
		
		keyword = "";
		unIndexed = "";
		unStored = "";
		text = "";
	}



	public Indexing(String indexDir, String keyword, String unIndexed,
			String unStored, String text) {
		super();
		this.indexDir = indexDir;
		this.keyword = keyword;
		this.unIndexed = unIndexed;
		this.unStored = unStored;
		this.text = text;
	}
	
	abstract void index() throws IOException;



	public String getIndexDir() {
		return indexDir;
	}



	public void setIndexDir(String indexDir) {
		this.indexDir = indexDir;
	}



	public String getKeyword() {
		return keyword;
	}



	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}



	public String getUnIndexed() {
		return unIndexed;
	}



	public void setUnIndexed(String unIndexed) {
		this.unIndexed = unIndexed;
	}



	public String getUnStored() {
		return unStored;
	}



	public void setUnStored(String unStored) {
		this.unStored = unStored;
	}



	public String getText() {
		return text;
	}



	public void setText(String text) {
		this.text = text;
	}
	
	
	
}
