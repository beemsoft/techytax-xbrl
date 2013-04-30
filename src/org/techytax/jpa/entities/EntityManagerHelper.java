package org.techytax.jpa.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerHelper {

	private static EntityManagerFactory emf= null;
	private static ThreadLocal<EntityManager> threadLocal = null;

//	static {
//		
//		Properties properties = new Properties() ;
//		try {
//			properties.load(EntityManagerHelper.class.getResourceAsStream("/hibernate.properties"));
//
//		
//		@SuppressWarnings("unchecked")
//		Map<String, String> propMap = new HashMap<String, String>((Map)properties);
////		propMap.put("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");
////		propMap.put("hibernate.connection.url", "jdbc:hsqldb:mem:testdb");
////		propMap.put("hibernate.connection.username", "sa");
////		propMap.put("hibernate.connection.password", "");
////		propMap.put("hibernate.connection.pool_size", "10");
////		propMap.put("hibernate.connection.autocommit", "true");
////		propMap.put("hibernate.cache.provider_class", "org.hibernate.cache.HashtableCacheProvider");
////		propMap.put("hibernate.hbm2ddl.auto", "create-drop");
////		propMap.put("hibernate.show_sql", "true");
////		propMap.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
//		emf = Persistence.createEntityManagerFactory("TechyTaxDB", propMap);
//		threadLocal = new ThreadLocal<EntityManager>();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	private static EntityManagerFactory getEntityManagerFactory() {
		if (emf == null) {
			Properties properties = new Properties();
			try {
				properties.load(EntityManagerHelper.class.getResourceAsStream("/hibernate.properties"));

				@SuppressWarnings("unchecked")
				Map<String, String> propMap = new HashMap<String, String>((Map) properties);
				emf = Persistence.createEntityManagerFactory("TechyTaxDB", propMap);
				threadLocal = new ThreadLocal<EntityManager>();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return emf;
	}
	
	public static void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		emf = entityManagerFactory;
		threadLocal = new ThreadLocal<EntityManager>();
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
