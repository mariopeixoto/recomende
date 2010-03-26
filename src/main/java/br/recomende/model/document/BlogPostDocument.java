package br.recomende.model.document;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;


@Entity
@Indexed
public class BlogPostDocument extends Document {
	
	private static final long serialVersionUID = 5678047638980814516L;

	private String url;
	
	@Field(index = Index.TOKENIZED)
	@Column(columnDefinition = "text")
	private String text;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
