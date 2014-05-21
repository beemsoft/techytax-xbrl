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
package org.techytax.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.techytax.domain.FiscalPeriod;
import org.techytax.domain.User;
import org.techytax.domain.UserObject;
import org.techytax.jpa.entities.EntityManagerHelper;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.zkplus.jpa.JpaUtil;

class BaseDao<T> {

	private EntityManager entityManager;
	private final Class<T> persistentClass;
	private boolean isForTesting = false;

	User user = UserCredentialManager.getUser();

	BaseDao(final Class<T> persistentClass) {
		entityManager = JpaUtil.getEntityManager();
		this.persistentClass = persistentClass;
	}

	public BaseDao(final EntityManager entityManager, final Class<T> persistentClass) {
		this.entityManager = entityManager;
		this.persistentClass = persistentClass;
		isForTesting = true;
	}

	private void getNewEntityManager() {
		if (isForTesting) {
			entityManager = EntityManagerHelper.getEntityManager();
		} else {
			entityManager = JpaUtil.getEntityManager();
		}
	}

	public void deleteEntity(T entity) {
		getNewEntityManager();
		try {
			entityManager.remove(entityManager.merge(entity));
			entityManager.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void persistEntity(T entity) {
		getNewEntityManager();
		try {
			entityManager.persist(entity);
			entityManager.flush();
			entityManager.clear();
		} catch (EntityExistsException e) {
			e.printStackTrace();
		}
	}

	public void merge(T entity) {
		getNewEntityManager();
		try {
			entityManager.merge(entity);
		} catch (EntityExistsException e) {
			e.printStackTrace();
		}
	}

	public Object getEntity(T entity, Long id) {
		Object retrievedEntity = null;
		getNewEntityManager();
		try {
			retrievedEntity = entityManager.getReference(entity.getClass(), id);
			return retrievedEntity;
		} catch (EntityExistsException e) {
			e.printStackTrace();
		}
		return retrievedEntity;
	}

	@SuppressWarnings("unchecked")
	List<T> findByNamedQuery(final String name, Object... params) {
		getNewEntityManager();
		Query query = entityManager.createNamedQuery(name);

		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
		addUserParameterForUserObjects(query);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	List<T> findByNamedQuery(String namedQueryName, Map<String, Object> parameters) {
		getNewEntityManager();
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

	private void addUserParameterForUserObjects(Query query) {
		if (UserObject.class.isAssignableFrom(persistentClass)) {
			query.setParameter("user", user);
		}
	}
	
	@SuppressWarnings("unchecked")
	List<T> findByNamedQueryForPeriod(String namedQueryName, FiscalPeriod period) {
		getNewEntityManager();
		Query query = entityManager.createNamedQuery(namedQueryName);
		query.setParameter("beginDate", period.getBeginDate(), TemporalType.DATE);
		query.setParameter("endDate", period.getEndDate(), TemporalType.DATE);
		addUserParameterForUserObjects(query);
		return query.getResultList();
	}	

	@SuppressWarnings("unchecked")
	T findEntityByNamedQuery(String namedQueryName, Map<String, Object> parameters) {
		getNewEntityManager();
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
	
	@SuppressWarnings("unchecked")
	T findEntityByNamedQuery(String namedQueryName) {
		getNewEntityManager();
		Query query = entityManager.createNamedQuery(namedQueryName);
		addUserParameterForUserObjects(query);
		try {
			return (T) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}	

	public List<T> findAll() throws IllegalAccessException {
		return findByCriteria();
	}

	List<T> findByCriteria(final Criterion... criterion) throws IllegalAccessException {
		return findByCriteria(-1, -1, criterion);
	}

	@SuppressWarnings("unchecked")
	List<T> findByCriteria(final int firstResult, final int maxResults, final Criterion... criterion) throws IllegalAccessException {
		getNewEntityManager();
		Session session = (Session) entityManager.getDelegate();
		Criteria crit = session.createCriteria(persistentClass);

		if (UserObject.class.isAssignableFrom(persistentClass)) {
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

}
