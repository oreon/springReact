
package com.td.bbwp.web.action.commerce;

import com.td.bbwp.commerce.CustomerOrder;
import org.witchcraft.base.entity.BaseRepository;

import java.util.stream.Stream;
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

import org.springframework.data.jpa.repository.Query;

import com.td.bbwp.commerce.CustomerOrder;

import com.td.bbwp.commerce.OrderItem;

//@RepositoryRestResource(exported=false)
public interface CustomerOrderRepositoryBase extends BaseRepository<CustomerOrder> {

	@Query("select e from CustomerOrder e")
	Stream<CustomerOrder> allEntities();

	Stream<CustomerOrder> findByNotesContainingAllIgnoringCase(@Param("notes") String notes);

	Stream<CustomerOrder> findByNotes(@Param("notes") String notes);
	Stream<CustomerOrder> findByNotesIgnoringCase(@Param("notes") String notes);

	Stream<CustomerOrder> findByCustomer(@Param("customer") com.td.bbwp.commerce.Customer customer);

	Stream<CustomerOrder> findByShipDate(@Param("shipDate") Date shipDate);

	Stream<CustomerOrder> findByPaymentMethod(@Param("paymentMethod") com.td.bbwp.commerce.PaymentMethod paymentMethod);

}
