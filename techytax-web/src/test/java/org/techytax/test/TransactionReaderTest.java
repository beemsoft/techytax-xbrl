package org.techytax.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jasypt.encryption.pbe.StandardPBEBigDecimalEncryptor;
import org.jasypt.encryption.pbe.StandardPBEBigIntegerEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.hibernate4.encryptor.HibernatePBEEncryptorRegistry;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.techytax.domain.Cost;
import org.techytax.domain.UserEntity;
import org.techytax.importing.helper.BaseTransactionReader;
import org.techytax.importing.helper.IngTransactionReader;
import org.techytax.jpa.dao.GenericDao;
import org.techytax.jpa.entities.EntityManagerHelper;
import org.techytax.jpa.entities.LogRecord;
import org.techytax.props.PropsFactory;

@Ignore
public class TransactionReaderTest {
	private GenericDao<LogRecord> logDao;
	private GenericDao<UserEntity> userDao;
	private EntityManagerFactory emf;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Before
	public void before() {
		Properties properties = new Properties();
		try {
			properties.load(System.class.getResourceAsStream("/hibernate.properties"));
			Map<String, String> propMap = new HashMap<>((Map) properties);
			emf = Persistence.createEntityManagerFactory("techyTaxPersistenceUnit", propMap);
			EntityManagerHelper.setEntityManagerFactory(emf);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		logDao = new GenericDao<>(EntityManagerHelper.getEntityManager(), LogRecord.class);
//		userDao = new GenericDao<>(EntityManagerHelper.getEntityManager(), UserEntity.class);
		
		initEncryption();
	}

	@Test
	public void testImporting() throws NumberFormatException, Exception {
		InputStream fis = TransactionReaderTest.class.getResourceAsStream("/transactions.txt");
		BaseTransactionReader rekeningFileHelper = new IngTransactionReader();
		List<Cost> result = rekeningFileHelper.readFile(new BufferedReader(new InputStreamReader(fis)));
		System.out.println("Klaar");
	}
	
	private void initEncryption() {
		StandardPBEStringEncryptor strongEncryptor = new StandardPBEStringEncryptor();
		StandardPBEBigDecimalEncryptor bigDecimalEncryptor = new StandardPBEBigDecimalEncryptor();
		StandardPBEBigIntegerEncryptor bigIntegerEncryptor = new StandardPBEBigIntegerEncryptor();
	
		try {
			String encryptionPassword = PropsFactory.getProperty("security.password");
			strongEncryptor.setPassword(encryptionPassword);
			bigDecimalEncryptor.setPassword(encryptionPassword);
			bigIntegerEncryptor.setPassword(encryptionPassword);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("TechyTax properties not found!");
		}
	
		HibernatePBEEncryptorRegistry registry = HibernatePBEEncryptorRegistry.getInstance();
		registry.registerPBEStringEncryptor("strongHibernateStringEncryptor", strongEncryptor);
		registry.registerPBEBigDecimalEncryptor("bigDecimalEncryptor", bigDecimalEncryptor);
		registry.registerPBEBigIntegerEncryptor("integerEncryptor", bigIntegerEncryptor);
	}	

}
