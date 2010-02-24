package br.recomende.model.curriculum;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CurriculumVitae {
	
	private Date atualizationDate;
	
	private String summary;
	
	private String otherRelevantInformation;
	
	private List<ScienceArea> scienceAreas;
	
	private List<BibliographicProduction> bibliographicProductions;
	
	private List<TecnicalProduction> tecnicalProductions;
	
	private List<Language> languages;
	
	public CurriculumVitae() {
		this.scienceAreas = new ArrayList<ScienceArea>();
		this.bibliographicProductions = new ArrayList<BibliographicProduction>();
		this.tecnicalProductions = new ArrayList<TecnicalProduction>();
		this.languages = new ArrayList<Language>();
	}
	
	public void setAtualizationDate(Date atualizationDate) {
		this.atualizationDate = atualizationDate;
	}

	public Date getAtualizationDate() {
		return atualizationDate;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary.toLowerCase();
	}

	public String getOtherRelevantInformation() {
		return otherRelevantInformation;
	}

	public void setOtherRelevantInformation(String otherRelevantInformation) {
		this.otherRelevantInformation = otherRelevantInformation.toLowerCase();
	}

	public void addScienceArea(ScienceArea scienceArea) {
		this.scienceAreas.add(scienceArea);
	}

	public List<ScienceArea> getScienceAreas() {
		return scienceAreas;
	}

	public List<BibliographicProduction> getBibliographicProductions() {
		return bibliographicProductions;
	}

	public void addBibliographicProduction(
			BibliographicProduction bibliographicProduction) {
		this.bibliographicProductions.add(bibliographicProduction);
	}

	public List<TecnicalProduction> getTecnicalProductions() {
		return tecnicalProductions;
	}

	public void addTecnicalProduction(TecnicalProduction tecnicalProduction) {
		this.tecnicalProductions.add(tecnicalProduction);
	}

	public void addLanguage(Language language) {
		this.languages.add(language);
	}
	
	public Language getLanguage(String name) {
		Languages languageType = Languages.getType(name);
		if (languageType != null) {
			for (Language language : this.languages) {
				if (language.getType() != null && language.getType().equals(languageType)) {
					return language;
				}
			}
		}
		return null;
	}
	
	public List<Language> getLanguages() {
		return this.languages;
	}
	
}
