package org.zkoss.zkplus.jpa;

import javax.persistence.EntityManager;

import org.techytax.jpa.entities.EntityManagerHelper;

public class JpaUtil {
	
	public static EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}
}
