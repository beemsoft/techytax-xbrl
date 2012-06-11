package org.techytax.zk.login;

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
		}
	}
}
