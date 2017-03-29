
package com.td.bbwp.commerce.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.Date;

import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.base.dto.BaseDto;
import java.math.BigDecimal;

public class PaymentMethodDto

		extends
			BaseDto

{

	protected String accountAddress;

	public void setAccountAddress(String accountAddress) {
		this.accountAddress = accountAddress;
	}

	public String getAccountAddress() {
		return accountAddress;
	}

}
