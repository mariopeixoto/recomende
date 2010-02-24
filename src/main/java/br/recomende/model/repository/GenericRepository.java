package br.recomende.model.repository;

import java.io.Serializable;
import java.util.Collection;

public interface GenericRepository <Type, PK extends Serializable> {
	
	Type put(Type object);
	void remove(Type object);
	void remove(PK identifier);
	Type get(PK identifier);
	Collection<Type> list();
	long size();
	
}
