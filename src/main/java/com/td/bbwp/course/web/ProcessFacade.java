package com.td.bbwp.course.web;

import java.util.Collection;
import java.util.Map;

import org.kie.api.task.model.TaskSummary;
import org.springframework.security.access.prepost.PreAuthorize;

import com.td.bbwp.wf.CaseInstance;
import com.td.bbwp.wf.TaskInstance;

/**
 * The main process facade interface - contains the common funcaitonality for all of basic workflow actions
 *  (should work across different bpms such as JBPM/Activiti etc)
 *  
 * @author singj2b
 *
 */
public interface ProcessFacade {

	CaseInstance launchProcess(String deploymentId, Long customerId, Map<String, Object> params);

	Collection<TaskSummary> getTasks();

	Collection<TaskSummary> getMyTasks();

	TaskInstance claimTask(long id);

	TaskInstance releaseTask(long id);

	TaskInstance startTask(long id);
	
	TaskInstance completeTask(Long id, Map<String, Object> data);

	CustomTask getTask(String id);

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	CaseInstance signalProcessInstance(Long id, String signal, String data);

}