package org.techytax.spring;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.jasypt.encryption.pbe.StandardPBEBigDecimalEncryptor;
import org.jasypt.encryption.pbe.StandardPBEBigIntegerEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.hibernate4.encryptor.HibernatePBEEncryptorRegistry;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"org.techytax"})
@EnableTransactionManagement
@EnableWebMvc
@PropertySources({
        @PropertySource("classpath:/application.properties"),
        @PropertySource(value = "file:application-override.properties", ignoreResourceNotFound = true)
})
public class ApplicationContext {
     
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
 
        initEncryption();
        
        return entityManagerFactoryBean;
    }
    
	private void initEncryption() {
		StandardPBEStringEncryptor strongEncryptor = new StandardPBEStringEncryptor();
		StandardPBEBigDecimalEncryptor bigDecimalEncryptor = new StandardPBEBigDecimalEncryptor();
		StandardPBEBigIntegerEncryptor bigIntegerEncryptor = new StandardPBEBigIntegerEncryptor();
	
		try {
			String encryptionPassword = environment.getProperty("security.password");
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
