
package com.td.bbwp.web.action.commerce;

import com.td.bbwp.commerce.Customer;
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

import com.td.bbwp.commerce.Customer;

//@RepositoryRestResource(exported=false)
public interface CustomerRepositoryBase extends BaseRepository<Customer> {

	Page<Customer> findByGender(@Param("gender") com.td.bbwp.commerce.Gender gender, Pageable pageable);

	Page<Customer> findByDob(@Param("dob") Date dob, Pageable pageable);

	Page<Customer> findByAddress(@Param("address") com.td.bbwp.commerce.Address address, Pageable pageable);

	Page<Customer> findByFirstNameContainingAllIgnoringCase(@Param("firstName") String firstName, Pageable pageable);

	Page<Customer> findByFirstName(@Param("firstName") String firstName, Pageable pageable);
	Page<Customer> findByFirstNameIgnoringCase(@Param("firstName") String firstName, Pageable pageable);

	Page<Customer> findByLastNameContainingAllIgnoringCase(@Param("lastName") String lastName, Pageable pageable);

	Page<Customer> findByLastName(@Param("lastName") String lastName, Pageable pageable);
	Page<Customer> findByLastNameIgnoringCase(@Param("lastName") String lastName, Pageable pageable);

}
