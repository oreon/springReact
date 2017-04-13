package com.td.bbwp.course.web;

import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.audit.AuditService;
import org.kie.api.runtime.manager.audit.ProcessInstanceLog;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.Task;
import org.kie.api.task.model.TaskSummary;
import org.kie.services.client.api.RemoteRuntimeEngineFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.td.bbwp.OptionsHelper;
import com.td.bbwp.commerce.Customer;
import com.td.bbwp.web.action.commerce.CustomerRepository;
import com.td.bbwp.web.action.wf.CaseDefinitionRepository;
import com.td.bbwp.web.action.wf.CaseInstanceRepository;
import com.td.bbwp.web.action.wf.TaskDefinitionRepository;
import com.td.bbwp.web.action.wf.TaskInstanceRepository;
import com.td.bbwp.wf.CaseDefinition;
import com.td.bbwp.wf.CaseInstance;
import com.td.bbwp.wf.CaseStatus;
import com.td.bbwp.wf.TaskDefinition;
import com.td.bbwp.wf.TaskInstance;
import com.td.bbwp.wf.TaskStatus;

/**
 * Created by singj2b on 3/16/2017.
 */
@Service
public  class JbpmTaskService implements ProcessFacade {

	public static final String READY = "Ready";

	

	private TaskService taskService;

	@Autowired
	CaseInstanceRepository caseInstanceRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	TaskInstanceRepository taskInstanceRepository;

	@Autowired
	TaskDefinitionRepository taskDefinitionRepository;

	@Autowired
	CaseDefinitionRepository caseDefinitionRepository;

	KieSession ksession;
	
	AuditService auditService;

	ObjectMapper mapper = new ObjectMapper();

	private TaskService getTaskService() {
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
			ex.printStackTrace();
		}

		String deploymentId = "com.td.bb:bb_aam:1.2";

		// String deploymentId = "demo:oneprocess:1.1";

		RuntimeEngine engine = RemoteRuntimeEngineFactory.newRestBuilder().addUrl(serverRestUrl).addTimeout(5)
				.addDeploymentId(deploymentId).addUserName(user).addPassword(password).disableTaskSecurity()

				// if you're sending custom class parameters, make sure that
				// the remote client instance knows about them!
				// .addExtraJaxbClasses(MyType.class)
				.build();

		// Create KieSession and TaskService instances and use them
		ksession = engine.getKieSession();
		taskService = engine.getTaskService();
		auditService = engine.getAuditService();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.td.bbwp.course.web.ProcessFacade#launchProcess(java.lang.String,
	 * java.lang.Long, java.util.Map)
	 */
	@Override
	public CaseInstance launchProcess(String deploymentId, Long customerId, Map<String, Object> params) {

		ProcessInstance processInstance = ksession.startProcess(deploymentId, params);
		CaseInstance caseInstance = createCaseInstance(deploymentId, processInstance.getId(), customerId);
		caseInstance.setStatus(CaseStatus.ACTIVE);
		caseInstanceRepository.save(caseInstance);
		return caseInstance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.td.bbwp.course.web.ProcessFacade#getTasks()
	 */
	@Override
	public Collection<TaskSummary> getTasks() {

		List<String> groups = Arrays.asList(new String[] { "lenders", "adjudicators" });

		List<TaskSummary> tasks = this.getTaskService().getTasksByGroup(groups);
		this.populateTasks(tasks);

		return tasks.stream().filter(task -> task.getStatusId().equalsIgnoreCase(READY)).collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.td.bbwp.course.web.ProcessFacade#getMyTasks()
	 */
	@Override
	public Collection<TaskSummary> getMyTasks() {

		String userId = getAuthUser();

		List<TaskSummary> tasks = getTaskService().getTasksOwned(userId, "en-UK");

		this.populateTasks(tasks);

		return tasks;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.td.bbwp.course.web.ProcessFacade#claimTask(long)
	 */
	@Override
	public TaskInstance claimTask(long id) {
		this.getTaskService().claim(id, this.getAuthUser());
		return updateTaskStatus(id, TaskStatus.RESERVED);
	}

	private TaskInstance updateTaskStatus(long id, TaskStatus status) {
		return taskInstanceRepository.findByTaskId(id).map(x -> doUpdateTaskStatus(x, status))
				.orElseThrow(() -> new RuntimeException("No task found for taskId " + id));

	}

	private TaskInstance doUpdateTaskStatus(TaskInstance x, TaskStatus status) {
		x.setStatus(status);
		return taskInstanceRepository.save(x);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.td.bbwp.course.web.ProcessFacade#releaseTask(long)
	 */
	@Override
	public TaskInstance releaseTask(long id) {
		this.getTaskService().release(id, this.getAuthUser());
		return updateTaskStatus(id, TaskStatus.READY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.td.bbwp.course.web.ProcessFacade#startTask(long)
	 */
	@Override
	public TaskInstance startTask(long id) {
		this.getTaskService().start(id, this.getAuthUser());
		return updateTaskStatus(id, TaskStatus.IN_PROGRESS);
	}

	void populateTasks(List<TaskSummary> tasks) {
		tasks.stream().forEach(x -> getCaseInstance(x));
		tasks.stream().forEach(x -> getTaskInstance(x));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.td.bbwp.course.web.ProcessFacade#getCaseInstance(org.kie.api.task.
	 * model.TaskSummary)
	 */
	//@Override
	public CaseInstance getCaseInstance(TaskSummary ts) {
		return caseInstanceRepository.findByProcessInstanceId(ts.getProcessInstanceId())
				.orElseGet(() -> createCaseInstanceByTaskSummary(ts, 1L));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.td.bbwp.course.web.ProcessFacade#getTaskInstance(org.kie.api.task.
	 * model.TaskSummary)
	 */
	//@Override
	public TaskInstance getTaskInstance(TaskSummary ts) {
		return taskInstanceRepository.findByTaskId(ts.getId()).orElseGet(() -> createTaskInstance(ts));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.td.bbwp.course.web.ProcessFacade#createCaseInstanceByTaskSummary(org.
	 * kie.api.task.model.TaskSummary, long)
	 */
	//@Override
	public CaseInstance createCaseInstanceByTaskSummary(TaskSummary ts, long cusotmerId) {
		return createCaseInstance(ts.getProcessId(), ts.getProcessInstanceId(), 1L);
	}

	private CaseInstance createCaseInstance(String processDef, long processInstanceId, long customerId) {
		CaseInstance caseInstance = new CaseInstance();

		CaseDefinition caseDef = OptionsHelper.getOrThrow(caseDefinitionRepository.findByName(processDef),
				new RuntimeException("No process configured " + processDef));

		Customer customer = OptionsHelper.getOrThrow(customerRepository.findById(customerId),
				new RuntimeException("No process configured " + processDef));

		caseInstance.setProcessInstanceId(processInstanceId);
		caseInstance.setCaseDefinition(caseDef);
		caseInstance.setCustomer(customer);

		caseInstanceRepository.save(caseInstance);
		return caseInstance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.td.bbwp.course.web.ProcessFacade#createTaskInstance(org.kie.api.task.
	 * model.TaskSummary)
	 */
	//@Override
	public TaskInstance createTaskInstance(TaskSummary x) {

		TaskDefinition taskDefinition = getTaskDefinitionByJbpmTaskId(x.getProcessId(), x.getName());

		TaskInstance taskInstance = new TaskInstance();
		taskInstance.setTaskId(x.getId());
		taskInstance.setTaskDefinition(taskDefinition);
		taskInstance.setName(x.getName());
		taskInstance.setCaseInstance(findCaseInstance(x.getProcessInstanceId()));
		taskInstanceRepository.save(taskInstance);
		return taskInstance;

	}

	private TaskDefinition getTaskDefinitionByJbpmTaskId(String processId, String taskName) {
		CaseDefinition caseDef = OptionsHelper.getOrThrow(caseDefinitionRepository.findByName(processId),
				new RuntimeException("No process configured " + processId));

		TaskDefinition taskDefinition = taskDefinitionRepository.findByNameAndCaseDefinition(taskName, caseDef).get();
		return taskDefinition;
	}
	
	private CaseInstance findCaseInstance(long processInstanceId){
		return OptionsHelper.getOrThrow(caseInstanceRepository.findByProcessInstanceId(processInstanceId),
				new RuntimeException("not caseInstance found for process instance id " + processInstanceId));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.td.bbwp.course.web.ProcessFacade#getTask(java.lang.String)
	 */
	@Override
	public CustomTask getTask(String id) {
		Long taskId = Long.parseLong(id);

		Task task = this.getTaskService().getTaskById(taskId);

		TaskInstance taskInstance = taskInstanceRepository.findByTaskId(taskId).map(x -> x)
				.orElseThrow(() -> new RuntimeException("no task instance found for " + taskId));

		CustomTask customTask = new CustomTask();
		customTask.setTaskInsance(taskInstance);

		BeanUtils.copyProperties(task, customTask);

		// taskDefinitionRepository.findByName(name, pageable)
		customTask.setSchema(taskInstance.getTaskDefinition().getForm());

		return customTask;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.td.bbwp.course.web.ProcessFacade#completeTask(java.lang.Long,
	 * java.util.Map)
	 */
	@Override
	public TaskInstance completeTask(Long id, Map<String, Object> data) {
		String userId = getAuthUser();

		try {
			this.getTaskService().complete(id, userId, data);

			TaskInstance current = OptionsHelper.getOrThrow(taskInstanceRepository.findByTaskId(id),
					new RuntimeException("not taskInstance found for task id " + id));

			current.setTaskData(mapper.writeValueAsString(data));

			current = taskInstanceRepository.save(current);
			doUpdateTaskStatus(current, TaskStatus.COMPLETED);

			updateCaseInstanceStatus(current);

			return current;
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error writing as json", e);
		}
	}

	/**
	 * Update the status if the case is completed - get from jbpm
	 * @param current
	 */
	private void updateCaseInstanceStatus(TaskInstance current) {
		CaseInstance currentCase = current.getCaseInstance();

		updateCaseInstanceStatus(currentCase);
	}

	private void updateCaseInstanceStatus(CaseInstance currentCase) {
		ProcessInstanceLog processLog = auditService.findProcessInstance(currentCase.getProcessInstanceId());

		if (processLog.getStatus() == ProcessInstance.STATE_COMPLETED) {
			currentCase.setStatus(CaseStatus.COMPLETE);
			//currentCase.setActive(active);
			caseInstanceRepository.save(currentCase);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.td.bbwp.course.web.ProcessFacade#signalProcessInstance(java.lang.
	 * Long, java.lang.String, java.lang.String)
	 */
	@Override
	public CaseInstance signalProcessInstance(final Long id, final String signal, String data) {
		CaseInstance caseInstance = findCaseInstance(id);
		ksession.signalEvent(signal, data, id);
		updateCaseInstanceStatus(caseInstance);
		return caseInstance;
	}

	protected String getAuthUser() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

}
