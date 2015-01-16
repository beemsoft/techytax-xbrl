/**
 * Copyright 2014 Hans Beemsterboer
 * 
 * This file is part of the TechyTax program.
 *
 * TechyTax is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * TechyTax is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TechyTax; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.techytax.jpa.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.techytax.domain.FiscalPeriod;
import org.techytax.domain.User;
import org.techytax.domain.UserObject;
import org.techytax.zk.login.UserCredentialManager;

public abstract class GenericDao<T> {

	@PersistenceContext
	private EntityManager entityManager;
	
	private final Class<T> persistentClass;

	public GenericDao() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        persistentClass = (Class) pt.getActualTypeArguments()[0];		
	}

	public GenericDao(final EntityManager entityManager, final Class<T> persistentClass) {
//		this.entityManager = entityManager;
		this.persistentClass = persistentClass;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Transactional
	public void deleteEntity(T entity) {
		try {
			entityManager.remove(entityManager.merge(entity));
			entityManager.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void persistEntity(T entity) {
		try {
			entityManager.persist(entity);
			entityManager.flush();
			entityManager.clear();
		} catch (EntityExistsException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void merge(T entity) {
		try {
			entityManager.merge(entity);
		} catch (EntityExistsException e) {
			e.printStackTrace();
		}
	}

	public Object getEntity(T entity, Long id) {
		Object retrievedEntity = null;
		try {
			retrievedEntity = entityManager.getReference(entity.getClass(), id);
			return retrievedEntity;
		} catch (EntityExistsException e) {
			e.printStackTrace();
		}
		return retrievedEntity;
	}

//	@SuppressWarnings("unchecked")
//	public
//	List<T> findByNamedQuery(final String name, Object... params) {
//		Query query = entityManager.createNamedQuery(name);
//
//		for (int i = 0; i < params.length; i++) {
//			query.setParameter(i + 1, params[i]);
//		}
//		addUserParameterForUserObjects(query);
//
//		return query.getResultList();
//	}
	
	@SuppressWarnings("unchecked")
	public
	List<T> findByNamedQuery(final String name, String... params) {
		Query query = entityManager.createNamedQuery(name);

		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
		addUserParameterForUserObjects(query);

		return query.getResultList();
	}	
	
	@Transactional
	public List<T> findAll() throws IllegalAccessException {
		return findByCriteria();
	}
	
	List<T> findByCriteria(final Criterion... criterion) throws IllegalAccessException {
		return findByCriteria(-1, -1, criterion);
	}

	@SuppressWarnings("unchecked")
	List<T> findByCriteria(final int firstResult, final int maxResults, final Criterion... criterion) throws IllegalAccessException {
		Session session = (Session) entityManager.getDelegate();
		Criteria crit = session.createCriteria(persistentClass);

		if (UserObject.class.isAssignableFrom(persistentClass)) {
			User user = UserCredentialManager.getUser();
			crit.add(Restrictions.eq("user", user));
		}

		for (final Criterion c : criterion) {
			crit.add(c);
		}

		if (firstResult > 0) {
			crit.setFirstResult(firstResult);
		}

		if (maxResults > 0) {
			crit.setMaxResults(maxResults);
		}

		final List<T> result = crit.list();
		return result;
	}	

	@Transactional
	public List<T> findAll(User user) throws IllegalAccessException {
		return findByCriteria(user);
	}

	protected List<T> findByCriteria(final User user, final Criterion... criterion) throws IllegalAccessException {
		return findByCriteria(-1, -1, user, criterion);
	}

	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(final int firstResult, final int maxResults, final User user, final Criterion... criterion) throws IllegalAccessException {
		Session session = (Session) entityManager.getDelegate();
		Criteria crit = session.createCriteria(persistentClass);

		if (user != null) {
			crit.add(Restrictions.eq("user", user));
		} else {
			throw new IllegalAccessException("Please logon first");
		}

		for (final Criterion c : criterion) {
			crit.add(c);
		}

		if (firstResult > 0) {
			crit.setFirstResult(firstResult);
		}

		if (maxResults > 0) {
			crit.setMaxResults(maxResults);
		}

		final List<T> result = crit.list();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	protected
	List<T> findByNamedQuery(String namedQueryName, Map<String, Object> parameters) {
		Set<Entry<String, Object>> rawParameters = parameters.entrySet();
		Query query = entityManager.createNamedQuery(namedQueryName);
		for (Entry<String, Object> entry : rawParameters) {
			if (entry.getValue() instanceof Date) {
				Date dateValue = (Date) entry.getValue();
				query.setParameter(entry.getKey(), dateValue, TemporalType.DATE);
			} else {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		addUserParameterForUserObjects(query);
		return query.getResultList();
	}	
	
	@SuppressWarnings("unchecked")
	List<T> findByNamedQueryForPeriod(String namedQueryName, FiscalPeriod period) {
		Query query = entityManager.createNamedQuery(namedQueryName);
		query.setParameter("beginDate", period.getBeginDate(), TemporalType.DATE);
		query.setParameter("endDate", period.getEndDate(), TemporalType.DATE);
		addUserParameterForUserObjects(query);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	protected
	T findEntityByNamedQuery(String namedQueryName) {
		Query query = entityManager.createNamedQuery(namedQueryName);
		addUserParameterForUserObjects(query);
		try {
			return (T) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	protected
	T findEntityByNamedQuery(String namedQueryName, Map<String, Object> parameters) {
		Set<Entry<String, Object>> rawParameters = parameters.entrySet();
		Query query = entityManager.createNamedQuery(namedQueryName);
		for (Entry<String, Object> entry : rawParameters) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		addUserParameterForUserObjects(query);
		try {
			return (T) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}	
	
	private void addUserParameterForUserObjects(Query query) {
		if (UserObject.class.isAssignableFrom(persistentClass)) {
			User user = UserCredentialManager.getUser();			
			query.setParameter("user", user);
		}
	}
	

}
