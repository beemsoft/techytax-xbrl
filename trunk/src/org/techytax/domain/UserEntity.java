package org.techytax.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "org.techytax.domain.UserEntity")
@Table(name = "user")
public class UserEntity extends User {

	private static final long serialVersionUID = 1L;
	
	public UserEntity() {
		// Default
	}
	
	public UserEntity(User user) {
		setUsername(user.getUsername());
		setId(user.getId());
	}

}
