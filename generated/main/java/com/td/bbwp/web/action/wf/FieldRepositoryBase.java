
package com.td.bbwp.web.action.wf;

import com.td.bbwp.wf.Field;
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

import com.td.bbwp.wf.Field;

//@RepositoryRestResource(exported=false)
public interface FieldRepositoryBase extends BaseRepository<Field> {

	@Query("select e from Field e")
	Stream<Field> allEntities();

	Stream<Field> findByNameContainingAllIgnoringCase(@Param("name") String name);

	Stream<Field> findByName(@Param("name") String name);
	Stream<Field> findByNameIgnoringCase(@Param("name") String name);

	Stream<Field> findByType(@Param("type") com.td.bbwp.wf.FieldType type);

	Stream<Field> findByTaskDefinition(@Param("taskDefinition") com.td.bbwp.wf.TaskDefinition taskDefinition);

	Stream<Field> findByRequired(@Param("required") Boolean required);

}
