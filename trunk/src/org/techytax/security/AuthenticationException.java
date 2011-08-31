package org.techytax.security;

public class AuthenticationException extends Exception {
	
	private static final long serialVersionUID = 468356082826079229L;
	private String message;
	
	public AuthenticationException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
