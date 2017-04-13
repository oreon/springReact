
/**
 * This file is created by Witchcraftmda only once .
 * It is OK to make changes as they will not be overwritten by subseuent re runs of the generator.
 */

package com.td.bbwp.commerce;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/** 
* Entity containing orders that a customer places. 
**/

@Entity
@Table(name = "CUSTOMER_ORDER")

@Inheritance(strategy = InheritanceType.JOINED) //inherit

public class CustomerOrder extends CustomerOrderBase implements java.io.Serializable {
	private static final long serialVersionUID = 702749209L;
}
