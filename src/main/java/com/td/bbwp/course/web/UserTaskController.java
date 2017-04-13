package com.td.bbwp.course.web;

import java.util.Collection;
import java.util.Map;

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
	private ProcessFacade processFacade;
	
	public static final String BB_AAM_AAM_LENDING = "bb_aam.aam_lending";
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Collection<TaskSummary> getTasks() {
		return processFacade.getTasks();
	}
	
	@RequestMapping(value = "/myTasks", method = RequestMethod.GET)
	public Collection<TaskSummary> getMyTasks() {
		
		return processFacade.getMyTasks();
	}

	
	@RequestMapping(value = "/launch", method = RequestMethod.POST)
	public ResponseEntity newProcessInstance(@RequestParam Long customerId/*, @RequestParam Map<String,String> processParams*/) {
		
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("paramName", new MyType("name", 23));		
		
		try {
			return new ResponseEntity(processFacade.launchProcess(BB_AAM_AAM_LENDING, customerId, null), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity("CaseInstance launch for customer " + customerId + "  failed due to " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public CustomTask getTask(@RequestParam String id) {
		return processFacade.getTask(id);
	}

	@RequestMapping(value = "/complete", method = RequestMethod.POST)
	public ResponseEntity completeTask(@RequestParam long id, @RequestBody Map<String, Object> data) {
		try {
			return new ResponseEntity(processFacade.completeTask(id, data), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity("Task " + id + " complete failed due to " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/claim", method = RequestMethod.POST)
	public ResponseEntity claimTask(@RequestParam long id) {
		try {
			return new ResponseEntity(processFacade.claimTask(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity("Task " + id + " claim failed due to " + e.getMessage(), HttpStatus.BAD_REQUEST) ;
		}
	}

	@RequestMapping(value = "/release", method = RequestMethod.POST)
	public ResponseEntity releaseTask(@RequestParam long id) {
		try {
			return new ResponseEntity(processFacade.releaseTask(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity("Task " + id + " release failed due to " + e.getMessage(), HttpStatus.BAD_REQUEST) ;
		}
	}

	@RequestMapping(value = "/start", method = RequestMethod.POST)
	public ResponseEntity startTask(@RequestParam long id) {
		try {
			return new ResponseEntity(processFacade.startTask(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity("Task " + id + " start failed due to " + e.getMessage(), HttpStatus.BAD_REQUEST) ;
		}
	}
	
	@RequestMapping(value = "/close", method = RequestMethod.POST)
	public ResponseEntity<?> closeProcess(@RequestParam Long id) {
		try {
			processFacade.signalProcessInstance(id, "cls", "cls");
			 return new ResponseEntity(id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity("Process Instance  " + id + " close failed due to  " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

	
}
