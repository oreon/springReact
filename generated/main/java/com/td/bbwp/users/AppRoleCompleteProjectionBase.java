
package com.td.bbwp.users;

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

import com.td.bbwp.users.AppRole;

import java.util.List;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "complete", types = {AppRole.class})
interface AppRoleCompleteProjectionBase {

	String getName();

	List<com.td.bbwp.users.AppUser> getAppUsers();

}
