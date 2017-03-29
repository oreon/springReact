
package com.td.bbwp.commerce.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.Date;

import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.base.dto.BaseDto;
import java.math.BigDecimal;

public class PersonDto

		extends
			BaseDto

{

	protected Gender gender;

	protected Date dob;

	protected AddressDto addressDto;

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Gender getGender() {
		return gender;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDob() {
		return dob;
	}

	public void setAddress(AddressDto addressDto) {
		this.addressDto = addressDto;
	}

	public AddressDto getAddress() {
		return addressDto;
	}

}
