package org.techytax.zk;

import org.techytax.domain.User;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Executions;

public class DemoModel {

	public boolean getLoggedOn() {
		return UserCredentialManager.getUser() != null;
	}

	public String getLoggedOnText() {
		String text = "Inloggen a.u.b.";
		User user = UserCredentialManager.getUser();
		if (user != null) {
			text = user.getCompanyName();
		}
		return text;
	}

	@Command
	public void logout() {
		UserCredentialManager.setUser(null);
		Executions.sendRedirect("login.zul");
	}

}