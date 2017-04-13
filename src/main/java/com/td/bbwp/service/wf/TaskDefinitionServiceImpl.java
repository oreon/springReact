
package com.td.bbwp.service.wf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.witchcraft.base.entity.BaseRepository;
import org.witchcraft.base.spring.BaseServiceImpl;

import com.td.bbwp.web.action.wf.TaskDefinitionRepository;
import com.td.bbwp.wf.TaskDefinition;

@Service
@Transactional
public class TaskDefinitionServiceImpl extends BaseServiceImpl<TaskDefinition> implements TaskDefinitionService {

	@Autowired
	private final TaskDefinitionRepository taskDefinitionRepository = null;

	@Override
	public BaseRepository<TaskDefinition> getRepository() {
		return taskDefinitionRepository;
	}
}
