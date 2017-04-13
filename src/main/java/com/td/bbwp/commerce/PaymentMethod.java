
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
@Table(name = "PAYMENT_METHOD")

@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //inherit

public class PaymentMethod extends PaymentMethodBase implements java.io.Serializable {
	private static final long serialVersionUID = -2005913728L;
}
