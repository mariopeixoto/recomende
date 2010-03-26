package br.recomende.model.profile;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;

@Entity
@Table(name = "users_tag")
@AccessType("field")
public class Tag implements Serializable {
	
	private static final long serialVersionUID = -6481056194003694079L;
	
	@EmbeddedId
	private TagPK pk;
	
	private Double weight;
	
	public Tag() {
		this.pk = new TagPK();
	}
	
	public Tag(String tag, Double weight) {
		this();
		this.pk.setTag(tag);
		this.weight = weight;
	}
	public Tag(String tag) {
		this();
		this.pk.setTag(tag);
	}
	
	public String getTag() {
		return this.pk.getTag();
	}
	
	public void setTag(String tag) {
		this.pk.setTag(tag);
	}
	
	public Double getWeight() {
		return weight;
	}
	
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
	public TagPK getPk() {
		return pk;
	}

	public void setPk(TagPK pk) {
		this.pk = pk;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}
	
}
