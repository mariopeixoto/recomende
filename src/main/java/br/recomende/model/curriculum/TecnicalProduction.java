package br.recomende.model.curriculum;

public class TecnicalProduction {
	
	private String title;
	private String englishTitle;
	private String purpose;
	private Integer year;
	private Boolean relevant;
	private String aditionalInformation;
	private String englishAditionalInformation;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title.toLowerCase();
	}
	
	public String getEnglishTitle() {
		return englishTitle;
	}
	
	public void setEnglishTitle(String englishTitle) {
		this.englishTitle = englishTitle.toLowerCase();
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
	
	public String getAditionalInformation() {
		return aditionalInformation;
	}
	
	public void setAditionalInformation(String aditionalInformation) {
		this.aditionalInformation = aditionalInformation.toLowerCase();
	}
	
	public String getEnglishAditionalInformation() {
		return englishAditionalInformation;
	}
	
	public void setEnglishAditionalInformation(String englishAditionalInformation) {
		this.englishAditionalInformation = englishAditionalInformation.toLowerCase();
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose.toLowerCase();
	}
	
}
