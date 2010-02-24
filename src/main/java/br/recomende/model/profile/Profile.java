package br.recomende.model.profile;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.MapKey;

@Entity
public class Profile implements Serializable {
	
	private static final long serialVersionUID = 3018693451709969007L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@CollectionOfElements(fetch=FetchType.EAGER)
	@MapKey(columns={@Column(name="tag")})
	@Column(name="weight")
	private Map<String,Double> profile;
	
	public Profile() {
		this.profile = new HashMap<String, Double>();
	}
	
	public Profile(Map<String,Double> profile) {
		this.profile = profile;
	}
	
	public Boolean add(String subject, Double weight) {
		if(!this.profile.containsKey(subject)) {
			this.profile.put(subject, weight);
			return true;
		}
		return false;
	}
	
	public Boolean edit(String subject,Double weight) {
		if (this.profile.containsKey(subject)){
			this.profile.put(subject, weight);
			return true;
		}
		return false;
	}
	
	public Double remove(String subject) {
		return this.profile.remove(subject);
	}
	
	public Map<String,Double> getElements() {
		return this.profile;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
