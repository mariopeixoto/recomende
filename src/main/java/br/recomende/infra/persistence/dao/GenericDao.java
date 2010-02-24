package br.recomende.infra.persistence.dao;

import java.io.Serializable;
import java.util.Collection;

public interface GenericDao <Type, PK extends Serializable> {

	Type persist(Type object);
	void delete(Type object);
	void delete(PK identifier);
	Type findByPK(PK identifier);
	Collection<Type> listAll();
	long count();
	
}
