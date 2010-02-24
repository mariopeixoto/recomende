package br.recomende.model.harvester;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.validator.NotNull;

@Entity
public class HarvesterDefinition {
	
	@Id
	private Integer id;

	@NotNull
	private String endPoint;
	
	private Date lastHarvest;
	
	@NotNull
	private Class<?> harvesterClass;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public Date getLastHarvest() {
		return lastHarvest;
	}

	public void setLastHarvest(Date lastHarvest) {
		this.lastHarvest = lastHarvest;
	}

	public Class<?> getHarvesterClass() {
		return harvesterClass;
	}

	public void setHarvesterClass(Class<?> harvesterClass) {
		this.harvesterClass = harvesterClass;
	}

}
