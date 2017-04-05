
package com.td.bbwp.web.action.commerce;

import com.td.bbwp.commerce.PayPal;
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

import com.td.bbwp.commerce.PayPal;

//@RepositoryRestResource(exported=false)
public interface PayPalRepositoryBase extends BaseRepository<PayPal> {

	@Query("select e from PayPal e")
	Stream<PayPal> allEntities();

	Stream<PayPal> findByAccountAddressContainingAllIgnoringCase(@Param("accountAddress") String accountAddress);

	Stream<PayPal> findByAccountAddress(@Param("accountAddress") String accountAddress);
	Stream<PayPal> findByAccountAddressIgnoringCase(@Param("accountAddress") String accountAddress);

	Stream<PayPal> findByPaypalAccountNumberContainingAllIgnoringCase(
			@Param("paypalAccountNumber") String paypalAccountNumber);

	Stream<PayPal> findByPaypalAccountNumber(@Param("paypalAccountNumber") String paypalAccountNumber);
	Stream<PayPal> findByPaypalAccountNumberIgnoringCase(@Param("paypalAccountNumber") String paypalAccountNumber);

}
