package br.recomende.infra.persistence.dao;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class GenericHibernateDao<Type,PK extends Serializable> implements GenericDao<Type, PK> {
	
	@PersistenceContext
	private EntityManager entityManager;

	private Class<?> objClass;
	
	public GenericHibernateDao(Class<?> clazz) {
		this.objClass = clazz;
	}
	
	public long count() {
		Criteria criteria = getSession().createCriteria(this.objClass).setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor=Throwable.class)
	public void delete(PK identifier) {
		Type object = this.findByPK(identifier);
		getSession().delete(object);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor=Throwable.class)
	public void delete(Type object) {
		getSession().lock(object, LockMode.NONE);
		getSession().delete(object);
	}

	@SuppressWarnings("unchecked")
	public Type findByPK(PK identifier) {
		try {
			return (Type) getSession().get(this.objClass, identifier);
		} catch (ObjectNotFoundException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<Type> listAll() {
		Criteria criteria = getSession().createCriteria(this.objClass);
		return criteria.list();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor=Throwable.class)
	public Type persist(Type object) {
		getSession().saveOrUpdate(object);
		return object;
	}
	
	protected Session getSession() {
		return (Session) this.entityManager.getDelegate();
	}
	
}
