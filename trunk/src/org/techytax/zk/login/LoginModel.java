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
