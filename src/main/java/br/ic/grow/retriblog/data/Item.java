package br.ic.grow.retriblog.data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;

@Entity
@Table(name="item")
@AccessType("property")
public class Item {
	
	Integer id;
	String title, excerpt, created, postupdate, permalink, completeText, 
	analyzedText, htmlText, category;
	
//	TechnoratiWeblog weblog;

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@Column(columnDefinition = "text")
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
	@Column(columnDefinition = "text")
	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}
	@Column(columnDefinition = "text")
	public String getCompleteText() {
		return completeText;
	}
	
	public void setCompleteText(String completeText) {
		this.completeText = completeText;
	}
	@Column(columnDefinition = "text")
	public String getAnalyzedText() {
		return analyzedText;
	}

	public void setAnalyzedText(String analyzedText) {
		this.analyzedText = analyzedText;
	}
	@Column(columnDefinition = "text")
	public String getHtmlText() {
		return htmlText;
	}

	public void setHtmlText(String htmlText) {
		this.htmlText = htmlText;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	
	
//	public TechnoratiWeblog getWeblog() {
//		return weblog;
//	}
//
//	public void setWeblog(TechnoratiWeblog weblog) {
//		this.weblog = weblog;
//	}

}
