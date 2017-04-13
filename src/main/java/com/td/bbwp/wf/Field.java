
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
@Table(name = "FIELD")

@Inheritance(strategy = InheritanceType.JOINED) // inherit

public class Field extends FieldBase implements java.io.Serializable {

	public Field() {

	}

	public Field(String name, FieldType type, boolean required) {
		this.name = name;
		this.type = type;
		this.required = required;
	}

	private static final long serialVersionUID = -864411485L;
}
