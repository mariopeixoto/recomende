package br.ic.grow.retriblog.data;

public class Weblog {

	String name, url, rssurl, atomurl, inboundblogs, inboundlinks, hasphoto, lastupdate;

	public void setInboundblogs(String inboundblogs) {
		this.inboundblogs = inboundblogs;
	}
	public void setInboundlinks(String inboundlinks) {
		this.inboundlinks = inboundlinks;
	}
	WeblogAuthor author;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRssurl() {
		return rssurl;
	}
	public void setRssurl(String rssurl) {
		this.rssurl = rssurl;
	}
	public String getAtomurl() {
		return atomurl;
	}
	public void setAtomurl(String atomurl) {
		this.atomurl = atomurl;
	}
	public String getHasphoto() {
		return hasphoto;
	}
	public void setHasphoto(String hasphoto) {
		this.hasphoto = hasphoto;
	}
	public String getLastupdate() {
		return lastupdate;
	}
	public void setLastupdate(String lastupdate) {
		this.lastupdate = lastupdate;
	}
	public WeblogAuthor getAuthor() {
		return author;
	}
	public void setAuthor(WeblogAuthor author) {
		this.author = author;
	}


}
