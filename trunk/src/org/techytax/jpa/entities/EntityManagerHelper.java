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
package org.techytax.jpa.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerHelper {

	private static EntityManagerFactory emf = null;
	private static ThreadLocal<EntityManager> threadLocal = new ThreadLocal<EntityManager>();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static EntityManagerFactory getEntityManagerFactory() {
		if (emf == null) {
			Properties properties = new Properties();
			try {
				properties.load(EntityManagerHelper.class.getResourceAsStream("/hibernate.properties"));
				Map<String, String> propMap = new HashMap<>((Map) properties);
				emf = Persistence.createEntityManagerFactory("TechyTaxDB", propMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return emf;
	}

	public static void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		emf = entityManagerFactory;
	}

	public static EntityManager getEntityManager() {
		EntityManager em = threadLocal.get();
		if (em == null) {
			em = getEntityManagerFactory().createEntityManager();
			threadLocal.set(em);
		}
		return em;
	}

	public static void closeEntityManager() {
		EntityManager em = threadLocal.get();
		if (em != null) {
			em.close();
			threadLocal.set(null);
		}
	}

	public static void closeEntityManagerFactory() {
		emf.close();
	}

	public static void beginTransaction() {
		getEntityManager().getTransaction().begin();
	}

	public static void rollback() {
		getEntityManager().getTransaction().rollback();
	}

	public static void commit() {
		getEntityManager().getTransaction().commit();
	}

	public static void joinTransaction() {
		getEntityManager().joinTransaction();
	}

	public static boolean isOpen() {
		return getEntityManager() != null && getEntityManager().isOpen();
	}
}
