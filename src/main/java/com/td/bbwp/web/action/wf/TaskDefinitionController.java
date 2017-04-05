
package com.td.bbwp.web.action.wf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.witchcraft.base.spring.BaseService;
import org.witchcraft.base.spring.BaseController;

import com.td.bbwp.service.wf.TaskDefinitionService;
import com.td.bbwp.wf.TaskDefinition;

@RestController
@RequestMapping("/rest/taskDefinitions")
public class TaskDefinitionController extends BaseController<TaskDefinition> {

	@Autowired
	private TaskDefinitionService taskDefinitionService;

	@Override
	public BaseService<TaskDefinition> getBaseService() {
		return taskDefinitionService;
	}

}
