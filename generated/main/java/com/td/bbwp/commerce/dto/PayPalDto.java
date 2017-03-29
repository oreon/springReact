
package com.td.bbwp.commerce.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.Date;

import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.base.dto.BaseDto;
import java.math.BigDecimal;

public class PayPalDto

		extends
			com.td.bbwp.commerce.dto.PaymentMethodDto

{

	protected String paypalAccountNumber;

	public void setPaypalAccountNumber(String paypalAccountNumber) {
		this.paypalAccountNumber = paypalAccountNumber;
	}

	public String getPaypalAccountNumber() {
		return paypalAccountNumber;
	}

}
