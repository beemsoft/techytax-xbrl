package org.techytax.struts.form;

import org.apache.struts.action.ActionForm;

public class DatabaseForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String catalog;
	private String host;
	private String password;
	private String username;

	public String getCatalog() {
		return catalog;
	}

	public String getHost() {
		return host;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
