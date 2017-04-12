
package com.td.bbwp.wf;

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

import com.td.bbwp.wf.CaseInstance;

import java.util.List;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "complete", types = {CaseInstance.class})
interface CaseInstanceCompleteProjectionBase {

	Optional<com.td.bbwp.wf.CaseDefinition> getCaseDefinition();

	List<com.td.bbwp.wf.TaskInstance> getTaskInstances();

	Optional<Long> getProcessInstanceId();

	Optional<String> getName();

	Optional<com.td.bbwp.wf.CaseStatus> getStatus();

	Optional<com.td.bbwp.commerce.Customer> getCustomer();

}
