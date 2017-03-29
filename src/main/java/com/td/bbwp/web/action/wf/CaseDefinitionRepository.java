
package com.td.bbwp.web.action.wf;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.td.bbwp.wf.CaseDefinition;

@RepositoryRestResource(exported=true)
public interface CaseDefinitionRepository extends CaseDefinitionRepositoryBase {
	
	@Query("select u from CaseDefinition u")
	Stream<CaseDefinition> findAllStream();


}
