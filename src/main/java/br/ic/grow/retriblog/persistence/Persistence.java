package br.ic.grow.retriblog.persistence;

import java.util.List;

import br.ic.grow.retriblog.data.Item;






public abstract class Persistence {
	
	String title, excerpt, created, postupdate, permalink, completeText, 
	analyzedText, category;
	
	Item item;
	
	
	
	public Persistence(String title, String excerpt, String created,
			String postupdate, String permalink, String completeText,
			String analyzedText, String category, Item item) {
		this.title = title;
		this.excerpt = excerpt;
		this.created = created;
		this.postupdate = postupdate;
		this.permalink = permalink;
		this.completeText = completeText;
		this.analyzedText = analyzedText;
		this.category = category;
		this.item = item;
	}
	
	public Persistence() {}

	abstract void salvarItem();
	 
	abstract void salvarItemItem();
	 
	abstract boolean checarPermalink();
	 
	abstract List<String> getCompleteTextFromCategory();
	 
	abstract List<String> getAnalyzedTextFromCategory();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getExcerpt() {
		return excerpt;
	}

	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getPostupdate() {
		return postupdate;
	}

	public void setPostupdate(String postupdate) {
		this.postupdate = postupdate;
	}

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public String getCompleteText() {
		return completeText;
	}

	public void setCompleteText(String completeText) {
		this.completeText = completeText;
	}

	public String getAnalyzedText() {
		return analyzedText;
	}

	public void setAnalyzedText(String analyzedText) {
		this.analyzedText = analyzedText;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	

}
