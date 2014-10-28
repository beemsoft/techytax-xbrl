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
import org.techytax.domain.UserEntity;
import org.techytax.jpa.dao.GenericDao;
import org.techytax.jpa.entities.EntityManagerHelper;
import org.techytax.jpa.entities.LogRecord;
import org.techytax.log.AuditType;

public class LogDaoTest {
	private GenericDao<LogRecord> logDao;
	private GenericDao<UserEntity> userDao;
	private EntityManagerFactory emf;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Before
	public void before() {
		Properties properties = new Properties();
		try {
			properties.load(LogDaoTest.class.getResourceAsStream("/hibernate.properties"));
			Map<String, String> propMap = new HashMap<>((Map) properties);
			emf = Persistence.createEntityManagerFactory("techyTaxPersistenceUnit", propMap);
			EntityManagerHelper.setEntityManagerFactory(emf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logDao = new GenericDao<>(EntityManagerHelper.getEntityManager(), LogRecord.class);
		userDao = new GenericDao<>(EntityManagerHelper.getEntityManager(), UserEntity.class);
	}

	@Test
	public void testStoreAndGetLogRecord() throws IllegalAccessException {
		UserEntity user = new UserEntity();
		user.setId(1L);
		addLogRecord(user);
		Collection<LogRecord> logRecords = logDao.findAll(user);
		assertEquals(1, logRecords.size());
	}

	private void addLogRecord(UserEntity user) {
		LogRecord logRecord = new LogRecord();
		logRecord.setAuditType(AuditType.CHECK_ACCOUNT);

		userDao.persistEntity(user);
		logRecord.setUser(user);
		logDao.persistEntity(logRecord);
	}

}
