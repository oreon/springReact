package com.td.bbwp.course.web;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

//import org.jbpm.services.api.model.UserTaskInstanceDesc;
import org.kie.api.task.model.Task;
import org.kie.api.task.model.TaskSummary;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.td.bbwp.OptionsHelper;
import com.td.bbwp.web.action.wf.CaseDefinitionRepository;
import com.td.bbwp.web.action.wf.CaseInstanceRepository;
import com.td.bbwp.web.action.wf.TaskDefinitionRepository;
import com.td.bbwp.web.action.wf.TaskInstanceRepository;
import com.td.bbwp.wf.CaseDefinition;
import com.td.bbwp.wf.CaseInstance;
import com.td.bbwp.wf.TaskDefinition;
import com.td.bbwp.wf.TaskInstance;

@RestController
@RequestMapping("/api/task")
public class UserTaskController {

	// @Autowired
	// private RuntimeDataService runtimeDataService;
	//
	// @Autowired
	// private DefinitionService definitionService;

	@Autowired
	private JbpmTaskService jbpmTaskService;

	@Autowired
	CaseInstanceRepository caseInstanceRepository;

	@Autowired
	TaskInstanceRepository taskInstanceRepository;

	@Autowired
	TaskDefinitionRepository taskDefinitionRepository;

	@Autowired
	CaseDefinitionRepository caseDefinitionRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Collection<TaskSummary> getTasks() {
		String userId = getAuthUser();

		// List<TaskSummary> tasks = jbpmTaskService.getTaskService()
		// .getTasksAssignedAsPotentialOwner(userId, "en-uk");
		List<String> groups = Arrays.asList(new String[] { "lenders" });

		List<TaskSummary> tasks = jbpmTaskService.getTaskService().getTasksByGroup(groups);

		populateTasks(tasks);

		return tasks.stream().filter(task -> task.getStatusId().equalsIgnoreCase("Ready")).collect(Collectors.toList());
	}

	private void populateTasks(List<TaskSummary> tasks) {
		tasks.stream().map(x -> getCaseInstance(x)).collect(Collectors.toList());
		tasks.stream().map(x -> getTaskInstance(x)).collect(Collectors.toList());

		// tasks.stream().flatMap(x ->
		// taskInstanceRepository.findByTaskId(x.getId())
		// .map(y -> y)
		// .orElseGet(() -> createTaskInstance(x)));
	}

	public CaseInstance getCaseInstance(TaskSummary ts) {
		// System.out.println(caseInstanceRepository.findByProcessInstanceId(ts.getProcessInstanceId()).get());
		return caseInstanceRepository.findByProcessInstanceId(ts.getProcessInstanceId())
				.orElseGet(() -> createCaseInstance(ts));
	}

	public TaskInstance getTaskInstance(TaskSummary ts) {
		// System.out.println(caseInstanceRepository.findByProcessInstanceId(ts.getProcessInstanceId()).get());
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

		CaseDefinition caseDef = OptionsHelper.getOrThrow(caseDefinitionRepository.findByName(x.getProcessId()),
				new RuntimeException("No process configured " + x.getProcessId()));

		TaskDefinition taskDefinition = taskDefinitionRepository.findByNameAndCaseDefinition(x.getName(), caseDef)
				.get();

		TaskInstance taskInstance = new TaskInstance();
		taskInstance.setTaskId(x.getId());
		taskInstance.setTaskDefinition(taskDefinition);
		taskInstance.setName(x.getName());
		taskInstance.setCaseInstance(caseInstanceRepository.findByProcessInstanceId(x.getProcessInstanceId()).get());
		taskInstanceRepository.save(taskInstance);
		return taskInstance;

	}

	@RequestMapping(value = "/myTasks", method = RequestMethod.GET)
	public Collection<TaskSummary> getMyTasks() {
		String userId = getAuthUser();

		List<TaskSummary> tasks = jbpmTaskService.getTaskService().getTasksOwned(userId, "en-uk");

		return tasks;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public CustomTask getTask(@RequestParam String id) {

		Long taskId = Long.parseLong(id);

		Task task = jbpmTaskService.getTaskService().getTaskById(taskId);

		CustomTask customTask = new CustomTask();

		BeanUtils.copyProperties(task, customTask);

		return customTask;

	}

	@RequestMapping(value = "/complete", method = RequestMethod.POST)
	public String completeTask(@RequestParam String id, @RequestBody Map<String, String> allRequestParams) {
		String userId = getAuthUser();

		Map<String, Object> data = new HashMap<String, Object>();

		for (Entry<String, String> entry : allRequestParams.entrySet()) {
			Object value = entry.getValue();
			// just a simple type conversion
			// integer
			try {
				value = Integer.parseInt(value.toString());
			} catch (NumberFormatException e) {
				// ignore
			}
			// boolean
			if (value.toString().equalsIgnoreCase("true") || value.toString().equalsIgnoreCase("false")) {
				value = Boolean.parseBoolean(value.toString());
			}
			data.put(entry.getKey(), value);
		}

		try {
			jbpmTaskService.getTaskService().complete(Long.parseLong(id), userId, data);
			return "Task " + id + " completed successfully";
		} catch (Exception e) {
			return "Task " + id + " complete failed due to " + e.getMessage();
		}

	}

	@RequestMapping(value = "/claim", method = RequestMethod.POST)
	public String claimTask(@RequestParam String id) {
		String userId = getAuthUser();
		try {
			jbpmTaskService.getTaskService().claim(Long.parseLong(id), userId);
			return "Task " + id + " claimed successfully";
		} catch (Exception e) {
			return "Task " + id + " claim failed due to " + e.getMessage();
		}
	}

	@RequestMapping(value = "/release", method = RequestMethod.POST)
	public String releaseTask(@RequestParam String id) {
		String userId = getAuthUser();
		try {
			jbpmTaskService.getTaskService().release(Long.parseLong(id), userId);
			return "Task " + id + " released successfully";
		} catch (Exception e) {
			return "Task " + id + " release failed due to " + e.getMessage();
		}

	}

	@RequestMapping(value = "/start", method = RequestMethod.POST)
	public String startTask(@RequestParam String id) {
		String userId = getAuthUser();
		try {
			jbpmTaskService.getTaskService().start(Long.parseLong(id), userId);
			return "Task " + id + " started successfully";
		} catch (Exception e) {
			return "Task " + id + " start failed due to " + e.getMessage();
		}

	}

	protected String getAuthUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userId = auth.getName();
		return userId;
	}
}
