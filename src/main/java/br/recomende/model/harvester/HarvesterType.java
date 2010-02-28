package br.recomende.model.harvester;

import javax.persistence.Entity;
import javax.persistence.Id;

import br.recomende.model.document.DocumentHarvester;

@Entity
public class HarvesterType {
	
	@Id
	private String type; 

	private Class<? extends DocumentHarvester> harvesterClass;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Class<? extends DocumentHarvester> getHarvesterClass() {
		return harvesterClass;
	}

	public void setHarvesterClass(Class<? extends DocumentHarvester> harvesterClass) {
		this.harvesterClass = harvesterClass;
	}
	
	@Override
	public String toString() {
		return type;
	}
	
}
