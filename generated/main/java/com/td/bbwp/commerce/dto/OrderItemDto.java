
package com.td.bbwp.commerce.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.Date;

import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.base.dto.BaseDto;
import java.math.BigDecimal;

public class OrderItemDto

		extends
			BaseDto

{

	protected Integer qty;

	protected ProductDto productDto;

	protected CustomerOrderDto customerOrderDto;

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getQty() {
		return qty;
	}

	public void setProduct(ProductDto productDto) {
		this.productDto = productDto;
	}

	public ProductDto getProduct() {
		return productDto;
	}

	public void setCustomerOrder(CustomerOrderDto customerOrderDto) {
		this.customerOrderDto = customerOrderDto;
	}

	public CustomerOrderDto getCustomerOrder() {
		return customerOrderDto;
	}

}
