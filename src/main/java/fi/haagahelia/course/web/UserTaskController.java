package fi.haagahelia.course.web;

//import org.jbpm.services.api.model.UserTaskInstanceDesc;
import org.kie.api.task.model.Task;
import org.kie.api.task.model.TaskSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@RestController
@RequestMapping("/task")
public class UserTaskController {

//	@Autowired
//	private RuntimeDataService runtimeDataService;
//
//	@Autowired
//	private DefinitionService definitionService;

	@Autowired
	private JbpmTaskService jbpmTaskService;


	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Collection<TaskSummary> getTasks() {		
	    String userId = getAuthUser();
	      
		List<TaskSummary> tasks = jbpmTaskService.getTaskService()
				.getTasksAssignedAsPotentialOwner(userId, "en-uk");

		return tasks;
 
	}
	
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public Task getTask(@RequestParam String id) {
		
		Long taskId = Long.parseLong(id);

		Task task = jbpmTaskService.getTaskService().getTaskById(taskId);

		return task;
 
	}
	
	@RequestMapping(value = "/complete", method = RequestMethod.POST)
	public String completeTask(@RequestParam String id, @RequestParam Map<String,String> allRequestParams) {		
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
