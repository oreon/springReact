
package com.td.bbwp.commerce.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.Date;

import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.base.dto.BaseDto;
import java.math.BigDecimal;

public class CreditCardDto

		extends
			com.td.bbwp.commerce.dto.PaymentMethodDto

{

	protected String ccNumber;

	protected Date expiry;

	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	public String getCcNumber() {
		return ccNumber;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}

	public Date getExpiry() {
		return expiry;
	}

}
