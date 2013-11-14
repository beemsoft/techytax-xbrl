/**
 * Copyright 2013 Hans Beemsterboer
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
package org.techytax.zk.login;

import java.util.Locale;

import org.techytax.domain.User;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

public class UserCredentialManager {

	private static final String KEY_USER_MODEL = UserCredentialManager.class.getName() + "_MODEL";

	private UserCredentialManager() {
	}

	public static User getUser() {
		return getUser(Sessions.getCurrent());
	}

	public static User getUser(Session zkSession) {
		synchronized (zkSession) {
			return (User) zkSession.getAttribute(KEY_USER_MODEL);
		}
	}

	public static void setUser(User user) {
		Session zkSession = Sessions.getCurrent();
		synchronized (zkSession) {
			zkSession.setAttribute(KEY_USER_MODEL, user);
			zkSession.setAttribute("user", user);
			zkSession.setAttribute("org.apache.struts.action.LOCALE", new Locale("nl"));
			zkSession.setAttribute("px_preferred_locale", new Locale("nl"));
		}
	}
}
