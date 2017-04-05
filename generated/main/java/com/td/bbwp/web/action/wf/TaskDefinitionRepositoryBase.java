
package com.td.bbwp.web.action.wf;

import com.td.bbwp.wf.TaskDefinition;
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

import com.td.bbwp.wf.TaskDefinition;

import com.td.bbwp.wf.Field;

//@RepositoryRestResource(exported=false)
public interface TaskDefinitionRepositoryBase extends BaseRepository<TaskDefinition> {

	@Query("select e from TaskDefinition e")
	Stream<TaskDefinition> allEntities();

	Stream<TaskDefinition> findByNameContainingAllIgnoringCase(@Param("name") String name);

	Stream<TaskDefinition> findByName(@Param("name") String name);
	Stream<TaskDefinition> findByNameIgnoringCase(@Param("name") String name);

	Stream<TaskDefinition> findByCaseDefinition(@Param("caseDefinition") com.td.bbwp.wf.CaseDefinition caseDefinition);

	Stream<TaskDefinition> findByFormSchemaContainingAllIgnoringCase(@Param("formSchema") String formSchema);

	Stream<TaskDefinition> findByFormSchema(@Param("formSchema") String formSchema);
	Stream<TaskDefinition> findByFormSchemaIgnoringCase(@Param("formSchema") String formSchema);

}
