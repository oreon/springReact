package com.td.bbwp.course.web;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.kie.api.runtime.process.ProcessInstance;
//import org.jbpm.services.api.ProcessService;
import org.kie.api.task.model.TaskSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task")
public class UserTaskController {


	@Autowired
	private JbpmTaskService jbpmTaskService;
	
	//@Autowired
	//private ProcessService processService;

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Collection<TaskSummary> getTasks() {
		String userId =jbpmTaskService.getAuthUser();

		String grp = userId.equalsIgnoreCase("krisv")?"lenders":"adjudicators";
		
		List<String> groups = Arrays.asList(new String[] { "lenders","adjudicators" });

		List<TaskSummary> tasks = jbpmTaskService.getTaskService().getTasksByGroup(groups);

		jbpmTaskService.populateTasks(tasks);

		return tasks.stream().filter(task -> task.getStatusId().equalsIgnoreCase("Ready")).collect(Collectors.toList());
	}

	
	
	@RequestMapping(value = "/launch", method = RequestMethod.POST)
	public Long newProcessInstance(@RequestParam String deploymentId, @RequestParam String processId,
			@RequestParam Map<String,String> processParams) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		//params.put("paramName", new MyType("name", 23));
	//	ProcessInstance processInstance = ksession.startProcess("bb_aam.aam_lending", params);
		
		long processInstanceId = jbpmTaskService.launchProcess("bb_aam", "aam_lending", params);
		
		return processInstanceId;
 
	}
	
	//= new HashMap<String, Object>();
			//params.put("paramName", new MyType("name", 23));
	

	@RequestMapping(value = "/myTasks", method = RequestMethod.GET)
	public Collection<TaskSummary> getMyTasks() {
		
		String userId =jbpmTaskService.getAuthUser();

		List<TaskSummary> tasks = jbpmTaskService.getTaskService().getTasksOwned(userId, "en-UK");
		
		jbpmTaskService.populateTasks(tasks);

		return tasks;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public CustomTask getTask(@RequestParam String id) {

		return jbpmTaskService.getTask(id);

	}

	@RequestMapping(value = "/complete", method = RequestMethod.POST)
	public ResponseEntity completeTask(@RequestParam String id, @RequestBody Map<String, Object> data) {
		//String userId =jbpmTaskService.getAuthUser();
		Long idLong = Long.parseLong(id);

		try {
			jbpmTaskService.completeTask(idLong, data);
			return new ResponseEntity("Task " + id + " completed successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity("Task " + id + " complete failed due to " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/claim", method = RequestMethod.POST)
	public String claimTask(@RequestParam String id) {
		String userId =jbpmTaskService.getAuthUser();
		try {
			jbpmTaskService.getTaskService().claim(Long.parseLong(id), userId);
			return "Task " + id + " claimed successfully";
		} catch (Exception e) {
			return "Task " + id + " claim failed due to " + e.getMessage();
		}
	}

	@RequestMapping(value = "/release", method = RequestMethod.POST)
	public String releaseTask(@RequestParam String id) {
		String userId =jbpmTaskService.getAuthUser();
		try {
			jbpmTaskService.getTaskService().release(Long.parseLong(id), userId);
			return "Task " + id + " released successfully";
		} catch (Exception e) {
			return "Task " + id + " release failed due to " + e.getMessage();
		}

	}

	@RequestMapping(value = "/start", method = RequestMethod.POST)
	public String startTask(@RequestParam String id) {
		String userId =jbpmTaskService.getAuthUser();
		try {
			jbpmTaskService.getTaskService().start(Long.parseLong(id), userId);
			return "Task " + id + " started successfully";
		} catch (Exception e) {
			return "Task " + id + " start failed due to " + e.getMessage();
		}
	}
	
	@RequestMapping(value = "/close", method = RequestMethod.POST)
	public String closeProcess(@RequestParam Long id) {
		try {
			jbpmTaskService.signalProcessInstance(id, "cls", "cls");
			return "Task " + id + " started successfully";
		} catch (Exception e) {
			return "Task " + id + " start failed due to " + e.getMessage();
		}
	}
	
	

	
}
