package org.techytax.zk.login;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.techytax.domain.User;
import org.techytax.security.AuthenticationException;
import org.techytax.security.SecurityService;
import org.techytax.security.SecurityServiceImpl;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Executions;

public class LoginModel  {
	
	private String username = "";
	
	private String password = "";

	@Command
	public void login() throws NoSuchAlgorithmException, UnsupportedEncodingException, AuthenticationException {
		SecurityService securityService = new SecurityServiceImpl();
		User user = securityService.authenticate(username, Sha.SHA1(password));
		UserCredentialManager.setUser(user);
		Executions.sendRedirect("/zul/vat.zul"); 
	}

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
	
	public String getUser() {
		User user = UserCredentialManager.getUser();
		if (user != null) {
			return user.getFullName();
		} else {
			return "";
		}
	}

	@Command
	public void logout() {
		UserCredentialManager.setUser(null);
		Executions.sendRedirect("login.zul");
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}	

}
