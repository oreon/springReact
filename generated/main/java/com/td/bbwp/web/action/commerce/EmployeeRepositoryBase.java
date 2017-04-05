
package com.td.bbwp.web.action.commerce;

import com.td.bbwp.commerce.Employee;
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

import com.td.bbwp.commerce.Employee;

//@RepositoryRestResource(exported=false)
public interface EmployeeRepositoryBase extends BaseRepository<Employee> {

	@Query("select e from Employee e")
	Stream<Employee> allEntities();

	Stream<Employee> findByGender(@Param("gender") com.td.bbwp.commerce.Gender gender);

	Stream<Employee> findByDob(@Param("dob") Date dob);

	Stream<Employee> findByAddress(@Param("address") com.td.bbwp.commerce.Address address);

	Stream<Employee> findByDepartment(@Param("department") com.td.bbwp.commerce.Department department);

	Stream<Employee> findByFirstNameContainingAllIgnoringCase(@Param("firstName") String firstName);

	Stream<Employee> findByFirstName(@Param("firstName") String firstName);
	Stream<Employee> findByFirstNameIgnoringCase(@Param("firstName") String firstName);

	Stream<Employee> findByLastNameContainingAllIgnoringCase(@Param("lastName") String lastName);

	Stream<Employee> findByLastName(@Param("lastName") String lastName);
	Stream<Employee> findByLastNameIgnoringCase(@Param("lastName") String lastName);

	Stream<Employee> findByCodeContainingAllIgnoringCase(@Param("code") String code);

	Stream<Employee> findByCode(@Param("code") String code);
	Stream<Employee> findByCodeIgnoringCase(@Param("code") String code);

}
