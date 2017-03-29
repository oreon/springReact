
/**
 *  WARN -  DO NOT MODIFY  - This file is generated by Witchcraftmda. Change PayPal.java instead
 *  Any changes will be overwritten by the next run of the code generator.
 */

package com.td.bbwp.commerce;

import javax.persistence.*;
import org.witchcraft.base.entity.FileAttachment;
import org.witchcraft.base.entity.BaseEntity;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.IndexColumn;

import javax.validation.constraints.*;

import java.math.BigDecimal;

import java.util.Optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.witchcraft.base.entity.BaseEntity;

@MappedSuperclass

//@Indexed
//@Analyzer(definition = "entityAnalyzer")

public abstract class PayPalBase extends com.td.bbwp.commerce.PaymentMethod {

	@Column(name = "PAYPAL_ACCOUNT_NUMBER", unique = false)

	protected String paypalAccountNumber

	;

	public void setPaypalAccountNumber(String paypalAccountNumber) {
		this.paypalAccountNumber = paypalAccountNumber;
	}

	public String getPaypalAccountNumber() {

		return paypalAccountNumber;
	}

	@Transient
	//Display name
	public String getDisplayName() {
		try {
			return accountAddress;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	/*
	public List<List<? extends BaseEntity>> getComposites(){
		List lst = new ArrayList();
		
		return lst;
	}
	*/

}
