
/**
 * This file is created by Witchcraftmda only once .
 * It is OK to make changes as they will not be overwritten by subseuent re runs of the generator.
 */

package com.td.bbwp.users;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "APP_USER")

@Inheritance(strategy = InheritanceType.JOINED) //inherit

public class AppUser extends AppUserBase implements java.io.Serializable {
	
	public AppUser(){}
	
	public AppUser(String name, String password) {
		this.userName = name;
		this.password = password;
	}

	private static final long serialVersionUID = -1510385050L;
}
