package br.recomende.model.profile;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.annotations.AccessType;

@Embeddable
@AccessType("property")
public class TagSet implements Serializable, Cloneable, Set<Tag> {
	
	private static final long serialVersionUID = 3018693451709969007L;
	
	private ConcurrentHashMap<String,Tag> tagMap;
	
	private Set<Tag> tags;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pk.user")
	@OrderBy("weight")
	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
		for(Tag tag : tags) {
			this.tagMap.put(tag.getTag(), tag);
		}
	}

	public TagSet() {
		this.tagMap = new ConcurrentHashMap<String, Tag>();
		this.tags = new HashSet<Tag>();
	}
	
	public TagSet(Map<String, Double> tags) {
		this();
		if (tags != null) {
			for(Entry<String, Double> tag : tags.entrySet()) {
				this.add(new Tag(tag.getKey(), tag.getValue()));
			}
		}
	}
	
	@Override
	public boolean add(Tag e) {
		boolean success = this.tags.add(e);
		if (success) {
			this.tagMap.put(e.getTag(), e);
		}
		return success;
	}

	@Override
	public boolean addAll(Collection<? extends Tag> c) {
		boolean success = this.tags.addAll(c);
		if (success) {
			for(Tag tag : c) {
				this.tagMap.put(tag.getTag(), tag);
			}
		}
		return success;
	}

	@Override
	public void clear() {
		this.tags.clear();
		this.tagMap.clear();
	}

	@Override
	public boolean contains(Object o) {
		return this.tags.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return this.tags.containsAll(c);
	}

	@Override
	@Transient
	public boolean isEmpty() {
		return this.tags.isEmpty();
	}

	@Override
	public Iterator<Tag> iterator() {
		return this.tags.iterator();
	}

	@Override
	public boolean remove(Object o) {
		if (o instanceof Tag) {
			Tag tag = this.tagMap.remove(((Tag)o).getTag());
			return this.tags.remove(tag);
		}
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		for (Object o : c) {
			if (!(o instanceof Tag)) {
				return false;
			}
		}
		for (Object o : c) {
			this.tagMap.remove(((Tag)o).getTag());
		}
		return this.tags.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		boolean success = this.tags.retainAll(c);
		if (success) {
			for(Tag tag : this.tags) {
				this.tagMap.put(tag.getTag(), tag);
			}
		}
		return success;
	}

	@Override
	public int size() {
		return this.tags.size();
	}

	@Override
	public Object[] toArray() {
		return this.tags.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return this.tags.toArray(a);
	}
	
	public boolean edit(Tag tag) {
		Tag o = this.tagMap.get(tag.getTag());
		if (o != null) {
			o.setWeight(tag.getWeight());
		}
		return false;
	}
	
	public Object clone() {
		TagSet clone = new TagSet();
		clone.tags = new HashSet<Tag>(this.tags);
		clone.tagMap = new ConcurrentHashMap<String, Tag>(this.tagMap);
		return clone;
	}
	
	public TagSet merge(TagSet set) {
		TagSet tagSet = (TagSet) this.clone();
		for(Tag tag : set) {
			Tag o = tagSet.tagMap.get(tag.getTag());
			if (o != null) {
				o.setWeight(o.getWeight() + tag.getWeight());
			} else {
				tagSet.add(tag);
			}
		}
		return tagSet;
	}
	
	public void crop(Double weight) {
		for (Tag tag : this.tags) {
			if (tag.getWeight() < weight) {
				this.remove(tag);
			}
		}
	}

}
