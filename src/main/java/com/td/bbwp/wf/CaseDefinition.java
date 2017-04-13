
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
@Table(name = "CASE_DEFINITION")

@Inheritance(strategy = InheritanceType.JOINED) //inherit

public class CaseDefinition extends CaseDefinitionBase implements java.io.Serializable {
	
	public CaseDefinition(){}
	
	public CaseDefinition(String name) {
		this.name = name;
	}

	private static final long serialVersionUID = -92291048L;
}
