package br.recomende.model.recommending;

public class BlogComment {
	
	private String blogPostPermalink;
	private String creator;
	
	public String getBlogPostPermalink() {
		return blogPostPermalink;
	}
	
	public void setBlogPostPermalink(String blogPostPermalink) {
		int i = blogPostPermalink.indexOf("#comment-");
		if (i != -1) {
			this.blogPostPermalink = blogPostPermalink.substring(0, i);
		} else {
			this.blogPostPermalink = blogPostPermalink;
		}
	}
	
	public String getCreator() {
		return creator;
	}
	
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
}
