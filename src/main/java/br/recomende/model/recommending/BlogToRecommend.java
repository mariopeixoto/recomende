package br.recomende.model.recommending;

import javax.persistence.Entity;
import javax.persistence.Id;

import br.recomende.model.document.Document;

@Entity
public class BlogToRecommend {
	
	@Id
	private String url;
	
	private Class<? extends Document> toNoCommentUsers;
	
	private Class<? extends Document> toFinalizedTopic;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Class<? extends Document> getToNoCommentUsers() {
		return toNoCommentUsers;
	}

	public void setToNoCommentUsers(Class<? extends Document> toNoCommentUsers) {
		this.toNoCommentUsers = toNoCommentUsers;
	}

	public Class<? extends Document> getToFinalizedTopic() {
		return toFinalizedTopic;
	}

	public void setToFinalizedTopic(Class<? extends Document> toFinalizedTopic) {
		this.toFinalizedTopic = toFinalizedTopic;
	}

}
