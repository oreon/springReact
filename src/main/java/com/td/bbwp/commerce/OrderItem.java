
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
@Table(name = "ORDER_ITEM")

@Inheritance(strategy = InheritanceType.JOINED) //inherit

public class OrderItem extends OrderItemBase implements java.io.Serializable {
	private static final long serialVersionUID = -411833402L;
}
