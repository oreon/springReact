
package com.td.bbwp.web.action.wf;

import java.util.Optional;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.td.bbwp.wf.CaseDefinition;
import com.td.bbwp.wf.TaskDefinition;

@RepositoryRestResource(exported=false)
public interface TaskDefinitionRepository extends TaskDefinitionRepositoryBase {
	
	Optional<TaskDefinition> findByNameAndCaseDefinition(String name, CaseDefinition caseDefinition);

}
