package br.recomende.model.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;

import br.recomende.model.harvester.HarvesterDefinition;
import br.recomende.model.recommender.api.Recommendable;

@Entity
@Indexed
public class DublinCoreDocument extends Recommendable {

	private static final long serialVersionUID = 803156250524041296L;
	
	@Field(index=Index.TOKENIZED)
	@Column(columnDefinition="text")
	private String title;

	@Column(columnDefinition="text")
	private String subject;
	
	@Column(columnDefinition="text")
	private String description;

	@Column(columnDefinition="text")
	private String creator;
	
	@Column(columnDefinition="text")
	private String publisher;
	
	@Column(columnDefinition="text")
	private String contributor;
	
	@Column(length=40)
	private String date;
	
	@Column(columnDefinition="text")
	private String docType;
	
	@Column(columnDefinition="text")
	private String format;
	
	@Column(columnDefinition="text")
	private String identifier;
	
	@Column(columnDefinition="text")
	private String source;
	
	@Column(columnDefinition="text")
	private String language;
	
	@Column(columnDefinition="text")
	private String relation;
	
	@Column(columnDefinition="text")
	private String coverage;
	
	@Column(columnDefinition="text")
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
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
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
	
	@Override
	public Map<String, String> getProperties() {
		Map<String,String> properties = new HashMap<String, String>();
		properties.put("authors", this.creator);
		properties.put("identifier", this.identifier);
		properties.put("pub.date", this.date);
		return properties;
	}
	
	
}
