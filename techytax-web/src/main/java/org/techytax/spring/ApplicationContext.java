package org.techytax.spring;

import java.util.Properties;

import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.jasypt.encryption.pbe.StandardPBEBigDecimalEncryptor;
import org.jasypt.encryption.pbe.StandardPBEBigIntegerEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.hibernate4.encryptor.HibernatePBEEncryptorRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.techytax.props.PropsFactory;
 
@Configuration
@ComponentScan(basePackages = {"org.techytax"})
@EnableTransactionManagement
@EnableWebMvc
//@ImportResource("classpath:applicationContext.xml")
//@PropertySource("classpath:application.properties")
public class ApplicationContext {
     
    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY = "hibernate.ejb.naming_strategy";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";
 
    @Resource
    private Environment environment;
 
    @Bean
    public DataSource dataSource() {
        JndiObjectFactoryBean dataSource = new JndiObjectFactoryBean();
        dataSource.setJndiName("java:comp/env/jdbc/TechyTaxDB");
        try {
            dataSource.afterPropertiesSet();
        } catch (IllegalArgumentException | NamingException e) {
            // rethrow
            throw new RuntimeException(e);
        }
        return (DataSource)dataSource.getObject(); 
    }
 
    @Bean
    public JpaTransactionManager transactionManager() throws ClassNotFoundException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
 
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
 
        return transactionManager;
    }
 
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() throws ClassNotFoundException {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
 
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan("org.techytax");
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
 
        Properties jpaProterties = new Properties();
        jpaProterties.put(PROPERTY_NAME_HIBERNATE_DIALECT, "org.hibernate.dialect.MySQLDialect");
//        jpaProterties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));
//        jpaProterties.put(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY));
//        jpaProterties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
 
        entityManagerFactoryBean.setJpaProperties(jpaProterties);
 
        initEncryption();
        
        return entityManagerFactoryBean;
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
