
package com.td.bbwp.web.action.wf;

import com.td.bbwp.wf.CaseInstance;
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

import com.td.bbwp.wf.CaseInstance;

import com.td.bbwp.wf.TaskInstance;

//@RepositoryRestResource(exported=false)
public interface CaseInstanceRepositoryBase extends BaseRepository<CaseInstance> {

	Page<CaseInstance> findByCaseDefinition(@Param("caseDefinition") com.td.bbwp.wf.CaseDefinition caseDefinition,
			Pageable pageable);

	Page<CaseInstance> findByProcessInstanceId(@Param("processInstanceId") Long processInstanceId, Pageable pageable);

}
