package com.td.bbwp.course.web;

import java.util.Collection;
import java.util.Map;

import org.kie.api.task.model.TaskSummary;

import com.td.bbwp.wf.CaseInstance;
import com.td.bbwp.wf.TaskInstance;

public interface ProcessFacade {

	CaseInstance launchProcess(String deploymentId, Long customerId, Map<String, Object> params);

	Collection<TaskSummary> getTasks();

	Collection<TaskSummary> getMyTasks();

	TaskInstance claimTask(long id);

	TaskInstance releaseTask(long id);

	TaskInstance startTask(long id);
	
	TaskInstance completeTask(Long id, Map<String, Object> data);

	CaseInstance getCaseInstance(TaskSummary ts);

	TaskInstance getTaskInstance(TaskSummary ts);

	CaseInstance createCaseInstanceByTaskSummary(TaskSummary ts, long cusotmerId);

	TaskInstance createTaskInstance(TaskSummary x);

	CustomTask getTask(String id);

	String signalProcessInstance(Long id, String signal, String data);

}