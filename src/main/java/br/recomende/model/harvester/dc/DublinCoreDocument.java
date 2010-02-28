package br.recomende.model.harvester.dc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;

import br.recomende.model.harvester.HarvesterDefinition;

@Entity
@Indexed
public class DublinCoreDocument extends br.recomende.model.document.Document {

	private static final long serialVersionUID = 803156250524041296L;
	
	@Field(index=Index.TOKENIZED)
	@Column(columnDefinition="text")
	private String title;

	@Column(columnDefinition="text")
	private String subject;
	
	@Column(columnDefinition="text")
	private String description;

	@Column(length=500)
	private String creator;
	
	@Column(length=500)
	private String publisher;
	
	@Column(length=500)
	private String contributor;
	
	@Column(length=500)
	private String date;
	
	@Column(length=500)
	private String type;
	
	@Column(length=500)
	private String format;
	
	@Column(length=500)
	private String identifier;
	
	@Column(length=500)
	private String source;
	
	@Column(length=500)
	private String language;
	
	@Column(length=500)
	private String relation;
	
	@Column(length=500)
	private String coverage;
	
	@Column(length=500)
	private String rights;
	
	@ManyToOne
	private HarvesterDefinition harvesterDefinition;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getContributor() {
		return contributor;
	}
	public void setContributor(String contributor) {
		this.contributor = contributor;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getCoverage() {
		return coverage;
	}
	public void setCoverage(String coverage) {
		this.coverage = coverage;
	}
	public String getRights() {
		return rights;
	}
	public void setRights(String rights) {
		this.rights = rights;
	}
	public HarvesterDefinition getHarvesterDefinition() {
		return harvesterDefinition;
	}
	public void setHarvesterDefinition(HarvesterDefinition harvesterDefinition) {
		this.harvesterDefinition = harvesterDefinition;
	}
	
	
}
