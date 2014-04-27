package org.techytax.test;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;
import org.techytax.domain.User;
import org.techytax.domain.UserEntity;
import org.techytax.jpa.dao.GenericDao;
import org.techytax.jpa.entities.EntityManagerHelper;
import org.techytax.jpa.entities.LogRecord;
import org.techytax.log.AuditType;

public class LogDaoTest {
	private GenericDao<LogRecord> logDao;
	private GenericDao<User> userDao;
	private EntityManagerFactory emf;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Before
	public void before() {
		Properties properties = new Properties();
		try {
			properties.load(LogDaoTest.class.getResourceAsStream("/hibernate-test.properties"));
			Map<String, String> propMap = new HashMap<>((Map) properties);
			emf = Persistence.createEntityManagerFactory("TechyTaxDB", propMap);
			EntityManagerHelper.setEntityManagerFactory(emf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logDao = new GenericDao<>(EntityManagerHelper.getEntityManager(), LogRecord.class);
		userDao = new GenericDao<>(EntityManagerHelper.getEntityManager(), User.class);
	}

	@Test
	public void testStoreAndGetLogRecord() throws IllegalAccessException {
		addLogRecord();
		Collection<LogRecord> logRecords = logDao.findAll(null);
		assertEquals(1, logRecords.size());
	}

	private void addLogRecord() {
		EntityManagerHelper.beginTransaction();
		LogRecord logRecord = new LogRecord();
		logRecord.setAuditType(AuditType.CHECK_ACCOUNT);
		User user = new User();
		user.setId(1L);
		userDao.persistEntity(user);
		logRecord.setUser((UserEntity) user);
		logDao.persistEntity(logRecord);
		EntityManagerHelper.commit();
	}

}
