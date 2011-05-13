package br.recomende.infra.persistence.dao;

import java.io.Serializable;
import java.util.Collection;

import br.recomende.model.repository.GenericRepository;

public class RepositoryWrapper<Type,PK extends Serializable> extends GenericHibernateDao<Type, PK> implements GenericRepository<Type, PK> {

	public Type get(PK identifier) {
		return this.findByPK(identifier);
	}

	public Collection<Type> list() {
		return this.listAll();
	}

	public Type put(Type object) {
		return this.persist(object);
	}

	public void remove(Type object) {
		this.delete(object);
	}

	public long size() {
		return this.count();
	}

	public void remove(PK identifier) {
		this.delete(identifier);
	}

	@Override
	public Object getDelegate() {
		return this.getSession();
	}
	
}
