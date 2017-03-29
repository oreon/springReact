
/**
 *  WARN -  DO NOT MODIFY  - This file is generated by Witchcraftmda. Change Address.java instead
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

public abstract class AddressBase {

	@Column(name = "STREET", unique = false)

	protected String street

	;

	@Column(name = "CITY", unique = false)

	protected String city

	;

	@Column(name = "PROVINCE", unique = false)

	protected String province

	;

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreet() {

		return street;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {

		return city;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvince() {

		return province;
	}

	@Transient
	//Display name
	public String getDisplayName() {
		try {
			return street;
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
