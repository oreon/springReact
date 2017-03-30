
package com.td.bbwp.web.action.wf;

import com.td.bbwp.wf.TaskInstance;
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

import com.td.bbwp.wf.TaskInstance;

//@RepositoryRestResource(exported=false)
public interface TaskInstanceRepositoryBase extends BaseRepository<TaskInstance> {

	Page<TaskInstance> findByTaskId(@Param("taskId") Long taskId, Pageable pageable);

	Page<TaskInstance> findByNameContainingAllIgnoringCase(@Param("name") String name, Pageable pageable);

	Page<TaskInstance> findByName(@Param("name") String name, Pageable pageable);
	Page<TaskInstance> findByNameIgnoringCase(@Param("name") String name, Pageable pageable);

	Page<TaskInstance> findByTaskDefinition(@Param("taskDefinition") com.td.bbwp.wf.TaskDefinition taskDefinition,
			Pageable pageable);

	Page<TaskInstance> findByCaseInstance(@Param("caseInstance") com.td.bbwp.wf.CaseInstance caseInstance,
			Pageable pageable);

}
