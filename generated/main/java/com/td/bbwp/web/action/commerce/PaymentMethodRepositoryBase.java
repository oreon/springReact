
package com.td.bbwp.web.action.commerce;

import com.td.bbwp.commerce.PaymentMethod;
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

import com.td.bbwp.commerce.PaymentMethod;

//@RepositoryRestResource(exported=false)
public interface PaymentMethodRepositoryBase extends BaseRepository<PaymentMethod> {

	@Query("select e from PaymentMethod e")
	Stream<PaymentMethod> allEntities();

	Stream<PaymentMethod> findByAccountAddressContainingAllIgnoringCase(@Param("accountAddress") String accountAddress);

	Stream<PaymentMethod> findByAccountAddress(@Param("accountAddress") String accountAddress);
	Stream<PaymentMethod> findByAccountAddressIgnoringCase(@Param("accountAddress") String accountAddress);

}
