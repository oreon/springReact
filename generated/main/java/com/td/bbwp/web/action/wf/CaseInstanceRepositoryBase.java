
package com.td.bbwp.web.action.wf;

import com.td.bbwp.wf.CaseInstance;
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

import com.td.bbwp.wf.CaseInstance;

import com.td.bbwp.wf.TaskInstance;

//@RepositoryRestResource(exported=false)
public interface CaseInstanceRepositoryBase extends BaseRepository<CaseInstance> {

	@Query("select e from CaseInstance e")
	Stream<CaseInstance> allEntities();

	Stream<CaseInstance> findByCaseDefinition(@Param("caseDefinition") com.td.bbwp.wf.CaseDefinition caseDefinition);

	Optional<CaseInstance> findByProcessInstanceId(@Param("processInstanceId") Long processInstanceId);

	Stream<CaseInstance> findByNameContainingAllIgnoringCase(@Param("name") String name);

	Stream<CaseInstance> findByName(@Param("name") String name);
	Stream<CaseInstance> findByNameIgnoringCase(@Param("name") String name);

	Stream<CaseInstance> findByString(@Param("string") com.td.bbwp.wf.status string);

}
