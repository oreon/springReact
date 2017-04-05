package com.td.bbwp.course.web;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.Task;
import org.kie.api.task.model.TaskSummary;
import org.kie.services.client.api.RemoteRuntimeEngineFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.td.bbwp.OptionsHelper;
import com.td.bbwp.web.action.wf.CaseDefinitionRepository;
import com.td.bbwp.web.action.wf.CaseInstanceRepository;
import com.td.bbwp.web.action.wf.TaskDefinitionRepository;
import com.td.bbwp.web.action.wf.TaskInstanceRepository;
import com.td.bbwp.wf.CaseDefinition;
import com.td.bbwp.wf.CaseInstance;
import com.td.bbwp.wf.TaskDefinition;
import com.td.bbwp.wf.TaskInstance;

/**
 * Created by singj2b on 3/16/2017.
 */
@Service
public class JbpmTaskService {

	private static final String BB_AAM_AAM_LENDING = "bb_aam.aam_lending";

	private TaskService taskService;

	@Autowired
	CaseInstanceRepository caseInstanceRepository;

	@Autowired
	TaskInstanceRepository taskInstanceRepository;

	@Autowired
	TaskDefinitionRepository taskDefinitionRepository;

	@Autowired
	CaseDefinitionRepository caseDefinitionRepository;
	
	KieSession ksession ;

	ObjectMapper mapper = new ObjectMapper();

	public TaskService getTaskService() {
		if (taskService == null)
			init();
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public void init() {
		String auth = SecurityContextHolder.getContext().getAuthentication().getName();

		String user = auth;
		String password = auth;

		URL serverRestUrl = null;

		try {
			serverRestUrl = new URL("http://localhost:8080/jbpm-console");
		} catch (Exception ex) {

		}

		String deploymentId = "com.td.bb:bb_aam:1.0";

		// String deploymentId = "demo:oneprocess:1.1";

		RuntimeEngine engine = RemoteRuntimeEngineFactory.newRestBuilder().addUrl(serverRestUrl).addTimeout(5)
				//.addDeploymentId(deploymentId)
				.addUserName(user).addPassword(password).disableTaskSecurity()

				// if you're sending custom class parameters, make sure that
				// the remote client instance knows about them!
				// .addExtraJaxbClasses(MyType.class)
				.build();

		// Create KieSession and TaskService instances and use them
		ksession = engine.getKieSession();
		taskService = engine.getTaskService();
	}
	
	public long launchProcess(String deploymentId, String processId, Map<String, Object> params){
		ProcessInstance processInstance = ksession.startProcess(BB_AAM_AAM_LENDING, params);
		return  processInstance.getId();
	}

	

	void populateTasks(List<TaskSummary> tasks) {
		tasks.stream().forEach(x -> getCaseInstance(x));
		tasks.stream().forEach(x -> getTaskInstance(x));
	}

	public CaseInstance getCaseInstance(TaskSummary ts) {
		// System.out.println(caseInstanceRepository.findByProcessInstanceId(ts.getProcessInstanceId()).get());
		return caseInstanceRepository.findByProcessInstanceId(ts.getProcessInstanceId())
				.orElseGet(() -> createCaseInstance(ts));
	}

	public TaskInstance getTaskInstance(TaskSummary ts) {
		return taskInstanceRepository.findByTaskId(ts.getId()).orElseGet(() -> createTaskInstance(ts));
	}

	public CaseInstance createCaseInstance(TaskSummary ts) {
		CaseInstance caseInstance = new CaseInstance();

		CaseDefinition caseDef = OptionsHelper.getOrThrow(caseDefinitionRepository.findByName(ts.getProcessId()),
				new RuntimeException("No process configured " + ts.getProcessId()));

		caseInstance.setProcessInstanceId(ts.getProcessInstanceId());
		caseInstance.setCaseDefinition(caseDef);

		caseInstanceRepository.save(caseInstance);
		return caseInstance;
	}

	public TaskInstance createTaskInstance(TaskSummary x) {

		TaskDefinition taskDefinition = getTaskDefinitionByJbpmTaskId(x.getProcessId(), x.getName());

		TaskInstance taskInstance = new TaskInstance();
		taskInstance.setTaskId(x.getId());
		taskInstance.setTaskDefinition(taskDefinition);
		taskInstance.setName(x.getName());
		taskInstance.setCaseInstance(caseInstanceRepository.findByProcessInstanceId(x.getProcessInstanceId()).get());
		taskInstanceRepository.save(taskInstance);
		return taskInstance;

	}

	private TaskDefinition getTaskDefinitionByJbpmTaskId(String processId, String taskName) {
		CaseDefinition caseDef = OptionsHelper.getOrThrow(caseDefinitionRepository.findByName(processId),
				new RuntimeException("No process configured " + processId));

		TaskDefinition taskDefinition = taskDefinitionRepository.findByNameAndCaseDefinition(taskName, caseDef).get();
		return taskDefinition;
	}

	public CustomTask getTask(String id) {
		Long taskId = Long.parseLong(id);

		Task task = this.getTaskService().getTaskById(taskId);

		String form = taskInstanceRepository.findByTaskId(taskId).map(x -> x.getTaskDefinition().getForm())
				.orElseThrow(() -> new RuntimeException("no task instance found for " + taskId));

		CustomTask customTask = new CustomTask();

		BeanUtils.copyProperties(task, customTask);

		// taskDefinitionRepository.findByName(name, pageable)
		customTask.setSchema(form);

		return customTask;
	}

	public String completeTask(Long id, Map<String, Object> data) {
		String userId = getAuthUser();

		try {
			//data.put("out_rework", Boolean.FALSE);
			this.getTaskService().complete(id, userId, data);

			TaskInstance current = OptionsHelper.getOrThrow(taskInstanceRepository.findByTaskId(id),
					new RuntimeException("not taskInstance found for task id " + id));

			current.setTaskData(mapper.writeValueAsString(data));
			taskInstanceRepository.save(current);
			return "Task " + id + " completed successfully";
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error writing as json", e);
		}
	}
	
	public String signalProcessInstance( Long id, String signal, String data) {
		ksession.signalEvent(signal, data, id);
		return "Signal sent to instance (" + id + ") successfully";
	}

	protected String getAuthUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userId = auth.getName();
		return userId;
	}

}
