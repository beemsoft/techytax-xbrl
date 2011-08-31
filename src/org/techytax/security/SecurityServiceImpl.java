package org.techytax.security;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.techytax.dao.UserDao;
import org.techytax.domain.User;
import org.techytax.util.DateHelper;

public class SecurityServiceImpl implements SecurityService {

	public User authenticate(String username, String password) throws AuthenticationException {

		UserDao userDao = new UserDao();
		User user = null;
		User resultUser = new User();

		try {
			user = userDao.getUser(username);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (user == null) {
			throw new AuthenticationException("Unknown user");
		}
		boolean passwordIsValid = user.passwordMatch(password);
		if (!passwordIsValid) {
			throw new AuthenticationException("Invalid password");
		}
		if (user.isBlocked()) {
			throw new AuthenticationException("User blocked");
		}
		try {
			BeanUtils.copyProperties(resultUser, user);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		user.setLatestOnlineTime(DateHelper.getTime(new Date()));
		try {
			userDao.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultUser;
	}
}
