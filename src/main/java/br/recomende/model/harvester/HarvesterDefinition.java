package br.recomende.model.harvester;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.validator.NotNull;

@Entity
public class HarvesterDefinition {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Column(unique=true)
	private String name;

	@NotNull
	private String endPoint;
	
	private Date lastHarvest;
	
	@NotNull
	@ManyToOne
	private HarvesterType harvesterType;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public HarvesterType getHarvesterType() {
		return harvesterType;
	}

	public void setHarvesterType(HarvesterType harvesterType) {
		this.harvesterType = harvesterType;
	}

}
