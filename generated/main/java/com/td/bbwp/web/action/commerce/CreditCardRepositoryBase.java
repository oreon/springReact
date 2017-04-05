
package com.td.bbwp.web.action.commerce;

import com.td.bbwp.commerce.CreditCard;
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

import com.td.bbwp.commerce.CreditCard;

//@RepositoryRestResource(exported=false)
public interface CreditCardRepositoryBase extends BaseRepository<CreditCard> {

	@Query("select e from CreditCard e")
	Stream<CreditCard> allEntities();

	Stream<CreditCard> findByAccountAddressContainingAllIgnoringCase(@Param("accountAddress") String accountAddress);

	Stream<CreditCard> findByAccountAddress(@Param("accountAddress") String accountAddress);
	Stream<CreditCard> findByAccountAddressIgnoringCase(@Param("accountAddress") String accountAddress);

	Stream<CreditCard> findByCcNumberContainingAllIgnoringCase(@Param("ccNumber") String ccNumber);

	Stream<CreditCard> findByCcNumber(@Param("ccNumber") String ccNumber);
	Stream<CreditCard> findByCcNumberIgnoringCase(@Param("ccNumber") String ccNumber);

	Stream<CreditCard> findByExpiry(@Param("expiry") Date expiry);

}
