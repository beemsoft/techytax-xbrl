/**
 * Copyright 2013 Hans Beemsterboer
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

import java.util.Collection;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.techytax.jpa.entities.LogRecord;

public class GenericDao<T> {
	private EntityManager entityManager;

	public GenericDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void deleteEntity(T entity) {
		try {
			entityManager.remove(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void persistEntity(T entity) {
		try {
			entityManager.persist(entity);
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

	@SuppressWarnings("unchecked")
	public Collection<LogRecord> findAllLogRecords() {
		Query query = entityManager.createQuery("SELECT lr FROM LogRecord lr");
		return (Collection<LogRecord>) query.getResultList();
	}
}
