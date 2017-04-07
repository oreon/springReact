
package com.td.bbwp.web.action.wf;

import com.td.bbwp.wf.TaskInstance;
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

import com.td.bbwp.wf.TaskInstance;

//@RepositoryRestResource(exported=false)
public interface TaskInstanceRepositoryBase extends BaseRepository<TaskInstance> {

	@Query("select e from TaskInstance e")
	Stream<TaskInstance> allEntities();

	Optional<TaskInstance> findByTaskId(@Param("taskId") Long taskId);

	Stream<TaskInstance> findByNameContainingAllIgnoringCase(@Param("name") String name);

	Stream<TaskInstance> findByName(@Param("name") String name);
	Stream<TaskInstance> findByNameIgnoringCase(@Param("name") String name);

	Stream<TaskInstance> findByTaskDefinition(@Param("taskDefinition") com.td.bbwp.wf.TaskDefinition taskDefinition);

	Stream<TaskInstance> findByCaseInstance(@Param("caseInstance") com.td.bbwp.wf.CaseInstance caseInstance);

	Stream<TaskInstance> findByTaskDataContainingAllIgnoringCase(@Param("taskData") String taskData);

	Stream<TaskInstance> findByTaskData(@Param("taskData") String taskData);
	Stream<TaskInstance> findByTaskDataIgnoringCase(@Param("taskData") String taskData);

	Stream<TaskInstance> findByStatusContainingAllIgnoringCase(@Param("status") String status);

	Stream<TaskInstance> findByStatus(@Param("status") String status);
	Stream<TaskInstance> findByStatusIgnoringCase(@Param("status") String status);

}
