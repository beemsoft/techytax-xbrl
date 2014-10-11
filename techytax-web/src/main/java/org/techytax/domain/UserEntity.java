package org.techytax.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@NamedQuery(name="UserEntity.findByName", query="SELECT user FROM UserEntity user WHERE user.username = ?") 
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
