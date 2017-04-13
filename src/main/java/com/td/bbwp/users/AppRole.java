
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
@Table(name = "APP_ROLE")

@Inheritance(strategy = InheritanceType.JOINED) // inherit

public class AppRole extends AppRoleBase implements java.io.Serializable {
	private static final long serialVersionUID = -1618836781L;

	public AppRole() {

	}

	public AppRole(String role) {
		this.name = role;
	}

}
