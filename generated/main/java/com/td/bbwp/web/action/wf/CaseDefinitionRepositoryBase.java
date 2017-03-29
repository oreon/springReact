
package com.td.bbwp.web.action.wf;

import com.td.bbwp.wf.CaseDefinition;
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

import com.td.bbwp.wf.CaseDefinition;

import com.td.bbwp.wf.TaskDefinition;

//@RepositoryRestResource(exported=false)
public interface CaseDefinitionRepositoryBase extends BaseRepository<CaseDefinition> {

	Page<CaseDefinition> findByNameContainingAllIgnoringCase(@Param("name") String name, Pageable pageable);

	Page<CaseDefinition> findByName(@Param("name") String name, Pageable pageable);
	Page<CaseDefinition> findByNameIgnoringCase(@Param("name") String name, Pageable pageable);

}
