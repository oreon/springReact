
package com.td.bbwp.web.action.commerce;

import com.td.bbwp.commerce.OrderItem;
import org.witchcraft.base.entity.BaseRepository;

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

import com.td.bbwp.commerce.OrderItem;

//@RepositoryRestResource(exported=false)
public interface OrderItemRepositoryBase extends BaseRepository<OrderItem> {

	Page<OrderItem> findByQty(@Param("qty") Integer qty, Pageable pageable);

	Page<OrderItem> findByProduct(@Param("product") com.td.bbwp.commerce.Product product, Pageable pageable);

	Page<OrderItem> findByCustomerOrder(@Param("customerOrder") com.td.bbwp.commerce.CustomerOrder customerOrder,
			Pageable pageable);

}
