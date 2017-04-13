
/**
 * This file is created by Witchcraftmda only once .
 * It is OK to make changes as they will not be overwritten by subseuent re runs of the generator.
 */

package com.td.bbwp.wf;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "CASE_INSTANCE")

@Inheritance(strategy = InheritanceType.JOINED) //inherit

public class CaseInstance extends CaseInstanceBase implements java.io.Serializable {
	private static final long serialVersionUID = 763848086L;
	
	@Override
	public boolean isActive() {
		return this.status == CaseStatus.ACTIVE;
	}
}
