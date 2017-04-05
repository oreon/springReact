
package com.td.bbwp.service.wf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.witchcraft.base.entity.BaseRepository;
import org.witchcraft.base.spring.BaseServiceImpl;

import com.td.bbwp.wf.TaskInstance;

import com.td.bbwp.web.action.wf.TaskInstanceRepository;

@Service
@Transactional
public class TaskInstanceServiceImpl extends BaseServiceImpl<TaskInstance> implements TaskInstanceService {

	@Autowired
	private final TaskInstanceRepository taskInstanceRepository = null;

	@Override
	public BaseRepository<TaskInstance> getRepository() {
		return taskInstanceRepository;
	}
}
