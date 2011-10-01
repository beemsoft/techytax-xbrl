/**
 * Copyright 2011 Hans Beemsterboer
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

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.techytax.dao.UserDao;
import org.techytax.domain.User;

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

		user.setLatestOnlineTime(new Date());
		try {
			userDao.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultUser;
	}
}
