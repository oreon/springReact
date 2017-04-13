
/**
 * This file is created by Witchcraftmda only once .
 * It is OK to make changes as they will not be overwritten by subseuent re runs of the generator.
 */

package com.td.bbwp.commerce;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "PAY_PAL")

@Inheritance(strategy = InheritanceType.JOINED) //inherit

public class PayPal extends PayPalBase implements java.io.Serializable {
	private static final long serialVersionUID = 852442228L;
}
