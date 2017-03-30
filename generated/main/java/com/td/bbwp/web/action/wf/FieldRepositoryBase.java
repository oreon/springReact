
package com.td.bbwp.web.action.wf;

import com.td.bbwp.wf.Field;
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

import com.td.bbwp.wf.Field;

//@RepositoryRestResource(exported=false)
public interface FieldRepositoryBase extends BaseRepository<Field> {

	Page<Field> findByNameContainingAllIgnoringCase(@Param("name") String name, Pageable pageable);

	Page<Field> findByName(@Param("name") String name, Pageable pageable);
	Page<Field> findByNameIgnoringCase(@Param("name") String name, Pageable pageable);

	Page<Field> findByType(@Param("type") com.td.bbwp.wf.FieldType type, Pageable pageable);

	Page<Field> findByTaskDefinition(@Param("taskDefinition") com.td.bbwp.wf.TaskDefinition taskDefinition,
			Pageable pageable);

	Page<Field> findByRequired(@Param("required") Boolean required, Pageable pageable);

}
