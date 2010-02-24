package br.recomende.model.curriculum;

public class ScienceArea {
	
	private String greatArea;
	private String area;
	private String subArea;
	private String specialization;
	
	public String getGreatArea() {
		return greatArea;
	}
	public void setGreatArea(String greatArea) {
		this.greatArea = greatArea.toLowerCase();
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area.toLowerCase();
	}
	public String getSubArea() {
		return subArea;
	}
	public void setSubArea(String subArea) {
		this.subArea = subArea.toLowerCase();
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization.toLowerCase();
	}

}
