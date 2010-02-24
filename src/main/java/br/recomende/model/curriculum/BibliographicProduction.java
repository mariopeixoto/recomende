package br.recomende.model.curriculum;

import java.util.ArrayList;
import java.util.List;

public class BibliographicProduction {

	private String title;
	private String englishTitle;
	private Integer year;
	private Boolean relevant;
	private List<String> keywords;
	private List<ScienceArea> areas;
	private String aditionalInformation;
	private String englishAditionalInformation;
	private String publicationExtraInformation;
	private String language;
	
	public BibliographicProduction() {
		this.keywords = new ArrayList<String>();
		this.areas = new ArrayList<ScienceArea>();
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getEnglishTitle() {
		return englishTitle;
	}
	
	public void setEnglishTitle(String englishTitle) {
		this.englishTitle = englishTitle;
	}
	
	public Integer getYear() {
		return year;
	}
	
	public void setYear(Integer year) {
		this.year = year;
	}
	
	public Boolean getRelevant() {
		return relevant;
	}
	
	public void setRelevant(Boolean relevant) {
		this.relevant = relevant;
	}
	
	public List<String> getKeywords() {
		return keywords;
	}
	
	public void addKeyword(String keyword) {
		this.keywords.add(keyword.toLowerCase());
	}
	
	public List<ScienceArea> getAreas() {
		return areas;
	}
	
	public void addArea(ScienceArea area) {
		this.areas.add(area);
	}
	
	public String getAditionalInformation() {
		return aditionalInformation;
	}
	
	public void setAditionalInformation(String aditionalInformation) {
		this.aditionalInformation = aditionalInformation;
	}
	
	public String getEnglishAditionalInformation() {
		return englishAditionalInformation;
	}
	
	public void setEnglishAditionalInformation(String englishAditionalInformation) {
		this.englishAditionalInformation = englishAditionalInformation;
	}
	
	public String getPublicationExtraInformation() {
		return publicationExtraInformation;
	}

	public void setPublicationExtraInformation(String publicationExtraInformation) {
		this.publicationExtraInformation = publicationExtraInformation;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
