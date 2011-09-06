package org.techytax.domain;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -374265857173724138L;

	private boolean blocked;

	private String email;

	private String fullName;

	private long id;

	private String latestOnlineTime;

	private String password;

	private String role;

	private String username;

	public String getEmail() {
		return email;
	}

	public String getFullName() {
		return fullName;
	}

	public long getId() {
		return id;
	}

	public String getLatestOnlineTime() {
		return latestOnlineTime;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public String getUsername() {
		return username;
	}

	public boolean hasRole(String role) {
		if (this.role != null && this.role.equals(role)) {
			return true;
		}
		return false;
	}

	public boolean isAdministrator() {
		return hasRole("admin");
	}
	
	public boolean isUser() {
		return hasRole("user");
	}	

	public boolean isBlocked() {
		return blocked;
	}

	public boolean isGuest() {
		return hasRole("guest");
	}

	public boolean passwordMatch(String pwd) {
		return password.equals(pwd);
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLatestOnlineTime(String latestOnlineTime) {
		this.latestOnlineTime = latestOnlineTime;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
