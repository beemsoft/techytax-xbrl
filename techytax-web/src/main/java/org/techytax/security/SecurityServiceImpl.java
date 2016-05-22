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
package org.techytax.security;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.NoResultException;

import org.apache.cxf.common.util.CollectionUtils;
import org.jasypt.encryption.pbe.StandardPBEBigDecimalEncryptor;
import org.jasypt.encryption.pbe.StandardPBEBigIntegerEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.hibernate4.encryptor.HibernatePBEEncryptorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.techytax.domain.User;
import org.techytax.domain.UserEntity;
import org.techytax.jpa.dao.UserEntityDao;
import org.techytax.log.AuditLogger;
import org.techytax.log.AuditType;
import org.techytax.zk.login.Sha;
import org.zkoss.util.resource.Labels;

@Service("securityService")
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private UserEntityDao userDao;
	
	@Autowired
	private AuditLogger auditLogger;

	@Resource
	private Environment environment;

	@Override
	public User authenticate(String username, String password) throws Exception {

		List<UserEntity> users;
		try {
			users = userDao.findByNamedQuery("UserEntity.findByName", username);
		} catch (NoResultException nre) {
			return null;
		}
		if (CollectionUtils.isEmpty(users)) {
			throw new AuthenticationException(Labels.getLabel("warning.unknown.user"));
		}
		UserEntity user = users.get(0);
		boolean passwordIsValid = user.passwordMatch(password);
		if (!passwordIsValid) {
			throw new AuthenticationException(Labels.getLabel("warning.invalid.password"));
		}
		if (user.isBlocked()) {
			throw new AuthenticationException("Deze gebruiker is geblokkeerd");
		}
		Date currentDate = new Date();
		try {
			user.setLatestOnlineTime(currentDate);
			userDao.merge(user);
			initEncryption();
			auditLogger.log(AuditType.LOGON, new UserEntity(user));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User authenticateForService(String username, String password) throws Exception {
		List<UserEntity> users;
		try {
			users = userDao.findByNamedQuery("UserEntity.findByName", username);
		} catch (NoResultException nre) {
			return null;
		}
		if (CollectionUtils.isEmpty(users)) {
			throw new AuthenticationException(Labels.getLabel("warning.unknown.user"));
		}
		UserEntity user = users.get(0);
		boolean passwordIsValid = user.passwordMatch(Sha.SHA1(password));
		if (!passwordIsValid) {
			throw new AuthenticationException(Labels.getLabel("warning.invalid.password"));
		}
		if (user.isBlocked()) {
			throw new AuthenticationException("Deze gebruiker is geblokkeerd");
		}
		try {
			initEncryption();
			auditLogger.log(AuditType.LOGON_FOR_SERVICE, new UserEntity(user));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
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
