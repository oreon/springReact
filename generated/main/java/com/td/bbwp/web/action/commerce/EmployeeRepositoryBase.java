
package com.td.bbwp.web.action.commerce;

import com.td.bbwp.commerce.Employee;
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

import com.td.bbwp.commerce.Employee;

//@RepositoryRestResource(exported=false)
public interface EmployeeRepositoryBase extends BaseRepository<Employee> {

	Page<Employee> findByGender(@Param("gender") com.td.bbwp.commerce.Gender gender, Pageable pageable);

	Page<Employee> findByDob(@Param("dob") Date dob, Pageable pageable);

	Page<Employee> findByAddress(@Param("address") com.td.bbwp.commerce.Address address, Pageable pageable);

	Page<Employee> findByDepartment(@Param("department") com.td.bbwp.commerce.Department department, Pageable pageable);

	Page<Employee> findByFirstNameContainingAllIgnoringCase(@Param("firstName") String firstName, Pageable pageable);

	Page<Employee> findByFirstName(@Param("firstName") String firstName, Pageable pageable);
	Page<Employee> findByFirstNameIgnoringCase(@Param("firstName") String firstName, Pageable pageable);

	Page<Employee> findByLastNameContainingAllIgnoringCase(@Param("lastName") String lastName, Pageable pageable);

	Page<Employee> findByLastName(@Param("lastName") String lastName, Pageable pageable);
	Page<Employee> findByLastNameIgnoringCase(@Param("lastName") String lastName, Pageable pageable);

	Page<Employee> findByCodeContainingAllIgnoringCase(@Param("code") String code, Pageable pageable);

	Page<Employee> findByCode(@Param("code") String code, Pageable pageable);
	Page<Employee> findByCodeIgnoringCase(@Param("code") String code, Pageable pageable);

}
