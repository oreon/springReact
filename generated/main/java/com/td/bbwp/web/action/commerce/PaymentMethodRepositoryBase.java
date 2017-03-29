
package com.td.bbwp.web.action.commerce;

import com.td.bbwp.commerce.PaymentMethod;
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

import com.td.bbwp.commerce.PaymentMethod;

//@RepositoryRestResource(exported=false)
public interface PaymentMethodRepositoryBase extends BaseRepository<PaymentMethod> {

	Page<PaymentMethod> findByAccountAddressContainingAllIgnoringCase(@Param("accountAddress") String accountAddress,
			Pageable pageable);

	Page<PaymentMethod> findByAccountAddress(@Param("accountAddress") String accountAddress, Pageable pageable);
	Page<PaymentMethod> findByAccountAddressIgnoringCase(@Param("accountAddress") String accountAddress,
			Pageable pageable);

}
