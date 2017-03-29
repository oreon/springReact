
package com.td.bbwp.web.action.commerce;

import com.td.bbwp.commerce.PayPal;
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

import com.td.bbwp.commerce.PayPal;

//@RepositoryRestResource(exported=false)
public interface PayPalRepositoryBase extends BaseRepository<PayPal> {

	Page<PayPal> findByAccountAddressContainingAllIgnoringCase(@Param("accountAddress") String accountAddress,
			Pageable pageable);

	Page<PayPal> findByAccountAddress(@Param("accountAddress") String accountAddress, Pageable pageable);
	Page<PayPal> findByAccountAddressIgnoringCase(@Param("accountAddress") String accountAddress, Pageable pageable);

	Page<PayPal> findByPaypalAccountNumberContainingAllIgnoringCase(
			@Param("paypalAccountNumber") String paypalAccountNumber, Pageable pageable);

	Page<PayPal> findByPaypalAccountNumber(@Param("paypalAccountNumber") String paypalAccountNumber, Pageable pageable);
	Page<PayPal> findByPaypalAccountNumberIgnoringCase(@Param("paypalAccountNumber") String paypalAccountNumber,
			Pageable pageable);

}
