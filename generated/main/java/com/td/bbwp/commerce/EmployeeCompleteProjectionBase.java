
package com.td.bbwp.commerce;

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

import java.util.List;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "complete", types = {Employee.class})
interface EmployeeCompleteProjectionBase {

	Optional<com.td.bbwp.commerce.Gender> getGender();

	Optional<Date> getDob();

	Optional<com.td.bbwp.commerce.Address> getAddress();

	Optional<com.td.bbwp.commerce.Department> getDepartment();

	Optional<String> getFirstName();

	Optional<String> getLastName();

	Optional<String> getCode();

}
