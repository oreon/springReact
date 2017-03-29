
package com.td.bbwp.commerce;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.math.BigDecimal;
import java.util.Date;

import java.util.Optional;

import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

import com.td.bbwp.commerce.CustomerOrder;

import java.util.List;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "complete", types = {CustomerOrder.class})
interface CustomerOrderCompleteProjectionBase {

	Optional<String> getNotes();

	Optional<com.td.bbwp.commerce.Customer> getCustomer();

	List<com.td.bbwp.commerce.OrderItem> getOrderItems();

	Optional<Date> getShipDate();

	Optional<com.td.bbwp.commerce.PaymentMethod> getPaymentMethod();

}
