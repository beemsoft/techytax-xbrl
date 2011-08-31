package org.techytax.security;

import org.techytax.domain.User;

public interface SecurityService {
	public User authenticate(String username, String password) throws AuthenticationException;
}
