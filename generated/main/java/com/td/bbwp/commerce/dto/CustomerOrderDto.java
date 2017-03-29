
package com.td.bbwp.commerce.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.Date;

import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.base.dto.BaseDto;
import java.math.BigDecimal;

public class CustomerOrderDto

		extends
			BaseDto

{

	protected String notes;

	protected CustomerDto customerDto;

	private List<OrderItemDto> orderItemsDto = new ArrayList<OrderItemDto>();

	protected Date shipDate;

	protected PaymentMethodDto paymentMethodDto;

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNotes() {
		return notes;
	}

	public void setCustomer(CustomerDto customerDto) {
		this.customerDto = customerDto;
	}

	public CustomerDto getCustomer() {
		return customerDto;
	}

	public void setOrderItems(List<OrderItemDto> orderItemsDto) {
		this.orderItemsDto = orderItemsDto;
	}
	public List<OrderItemDto> getOrderItems() {
		return orderItemsDto;
	}

	public void addOrderItem(OrderItemDto orderItemDto) {

		orderItemDto.setCustomerOrder((CustomerOrderDto) this);

		if (this.orderItemsDto == null) {
			this.orderItemsDto = new ArrayList<OrderItemDto>();
		}

		this.orderItemsDto.add(orderItemDto);
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	public Date getShipDate() {
		return shipDate;
	}

	public void setPaymentMethod(PaymentMethodDto paymentMethodDto) {
		this.paymentMethodDto = paymentMethodDto;
	}

	public PaymentMethodDto getPaymentMethod() {
		return paymentMethodDto;
	}

}
