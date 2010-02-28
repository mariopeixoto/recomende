package br.recomende.model.profile;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.Map.Entry;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;

import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.MapKey;

@Embeddable
public class Profile implements Serializable {
	
	private static final long serialVersionUID = 3018693451709969007L;
	
	private class ElementsComparator implements Comparator<Map.Entry<String, Double>>{

		@Override
		public int compare(Entry<String, Double> entry1, Entry<String, Double> entry2) {
			// assuming there are no null keys or values  
			int diff = entry2.getValue().compareTo(entry1.getValue());  
			if (diff == 0)  {  
				diff = entry1.getKey().compareTo(entry2.getKey());  
			}  
			return diff;  
		}
		
	};
	
	@CollectionOfElements(fetch=FetchType.EAGER)
	@MapKey(columns={@Column(name="tag")})
	@Column(name="weight", precision=4)
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
	
	public Collection<Entry<String,Double>> getElements() {
		Collection<Entry<String, Double>> elements = new TreeSet<Entry<String,Double>>(new ElementsComparator());
		elements.addAll(this.profile.entrySet());
		return elements;
	}
	
}
