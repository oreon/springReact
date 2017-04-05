
package com.td.bbwp.web.action.commerce;

import com.td.bbwp.commerce.Customer;
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

import com.td.bbwp.commerce.Customer;

//@RepositoryRestResource(exported=false)
public interface CustomerRepositoryBase extends BaseRepository<Customer> {

	@Query("select e from Customer e")
	Stream<Customer> allEntities();

	Stream<Customer> findByGender(@Param("gender") com.td.bbwp.commerce.Gender gender);

	Stream<Customer> findByDob(@Param("dob") Date dob);

	Stream<Customer> findByAddress(@Param("address") com.td.bbwp.commerce.Address address);

	Stream<Customer> findByFirstNameContainingAllIgnoringCase(@Param("firstName") String firstName);

	Stream<Customer> findByFirstName(@Param("firstName") String firstName);
	Stream<Customer> findByFirstNameIgnoringCase(@Param("firstName") String firstName);

	Stream<Customer> findByLastNameContainingAllIgnoringCase(@Param("lastName") String lastName);

	Stream<Customer> findByLastName(@Param("lastName") String lastName);
	Stream<Customer> findByLastNameIgnoringCase(@Param("lastName") String lastName);

}
