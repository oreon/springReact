
package com.td.bbwp.web.action.wf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.witchcraft.base.spring.BaseService;
import org.witchcraft.base.spring.BaseController;

import com.td.bbwp.service.wf.TaskInstanceService;
import com.td.bbwp.wf.TaskInstance;

@RestController
@RequestMapping("/rest/taskInstances")
public class TaskInstanceController extends BaseController<TaskInstance> {

	@Autowired
	private TaskInstanceService taskInstanceService;

	@Override
	public BaseService<TaskInstance> getBaseService() {
		return taskInstanceService;
	}

}
