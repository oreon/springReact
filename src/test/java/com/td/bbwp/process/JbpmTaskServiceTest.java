package com.td.bbwp.process;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.kie.api.task.model.TaskSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.td.bbwp.MainApp;
import com.td.bbwp.course.web.ProcessFacade;
import com.td.bbwp.course.web.UserTaskController;
import com.td.bbwp.wf.CaseInstance;
import com.td.bbwp.wf.CaseStatus;
import com.td.bbwp.wf.TaskInstance;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MainApp.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class JbpmTaskServiceTest {

	private final static String NEW_JOURNAL_NAME = "New Journal";

	@Autowired
	private ProcessFacade jbpmTaskService;

	@Autowired
	private AuthenticationManager authenticationManager;

	static TaskSummary writeupTask;

	static CaseInstance caseInstance;

	private static boolean initialized = false;

	@After
	@WithUserDetails("admin")
	public void closeAaam() {

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken("admin", "admin");

		// Authenticate the user
		Authentication authentication = authenticationManager.authenticate(authRequest);
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
		SecurityContextHolder.setContext(securityContext);

		System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
		// System.out.println(authentication.getName());
		try {
			jbpmTaskService.signalProcessInstance(caseInstance.getProcessInstanceId(), "cls", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Before
	@WithUserDetails("krisv")
	public void launchAaam() {
		if (!initialized) {
			caseInstance = launchProcess();
			initialized = true;
		}
	}

	private CaseInstance launchProcess() {
		int grpTasksBefore = jbpmTaskService.getTasks().size();
		Collection<TaskSummary> beforeTasks = jbpmTaskService.getTasks();
		CaseInstance caseInstanceLaunched = jbpmTaskService.launchProcess(UserTaskController.BB_AAM_AAM_LENDING, 1L,
				null);
		assertNotNull(caseInstanceLaunched);
		assertEquals(caseInstanceLaunched.getStatus(), CaseStatus.ACTIVE);
		assertEquals(1 + grpTasksBefore, jbpmTaskService.getTasks().size());
		Collection<TaskSummary> newlyCreated = jbpmTaskService.getTasks();
		newlyCreated.removeAll(beforeTasks);
		writeupTask = newlyCreated.iterator().next();
		caseInstance = caseInstanceLaunched;
		return caseInstanceLaunched;

	}

	@Test
	@WithUserDetails("krisv")
	public void claimTask() {
		int grpTasksBefore = jbpmTaskService.getTasks().size();
		System.out.println("tyring to claim task " + writeupTask.getId());
		assertNotNull(jbpmTaskService.claimTask(writeupTask.getId()));
		assertEquals(grpTasksBefore - 1, jbpmTaskService.getTasks().size());
		// releaseTask();
	}

	@Test(expected = AccessDeniedException.class)
	@WithUserDetails("krisv")
	public void closeCaseNotAllowed() {
		CaseInstance cas = launchProcess();
		jbpmTaskService.signalProcessInstance(cas.getProcessInstanceId(), "cls", null);
		// TODO: figure a way to close the launched process
	}

	@Test
	@WithUserDetails("admin")
	public void closeCase() {
		CaseInstance cas = launchProcess();
		cas = jbpmTaskService.signalProcessInstance(cas.getProcessInstanceId(), "cls", null);
		assertEquals(cas.getStatus(), CaseStatus.COMPLETE);
	}

	@Test
	@WithUserDetails("krisv")
	public void startTask() {
		launchProcess();
		int grpTasksBefore = jbpmTaskService.getTasks().size();
		attemptClaim(writeupTask.getId());
		assertNotNull(jbpmTaskService.startTask(writeupTask.getId()));
	}

	// @Test
	@WithUserDetails("krisv")
	public void releaseTask() {
		int grpTasksBefore = jbpmTaskService.getTasks().size();
		attemptClaim(writeupTask.getId());
		assertNotNull(jbpmTaskService.releaseTask(writeupTask.getId()));
		assertEquals(1 + grpTasksBefore, jbpmTaskService.getTasks().size());
	}

	public void attemptClaim(long id) {
		try {
			jbpmTaskService.claimTask(id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	@WithUserDetails("krisv")
	public void completeTask() {
		launchProcess();
		int myTasksBefore = jbpmTaskService.getMyTasks().size();

		Map<String, Object> data = new HashMap<>();
		data.put("color", "red");
		data.put("risk_rating", "red");

		attemptClaim(writeupTask.getId());

		assertNotNull(jbpmTaskService.startTask(writeupTask.getId()));
		assertNotNull(jbpmTaskService.completeTask(writeupTask.getId(), data));
		// assertEquals(myTasksBefore -1, jbpmTaskService.getMyTasks().size());

		TaskSummary assess = jbpmTaskService.getTasks().iterator().next();

		attemptClaim(assess.getId());
		TaskInstance assessTask = jbpmTaskService.startTask(assess.getId());
		// assertEquals(assessTask.getCaseInstance().getStatus() ,
		// CaseStatus.ACTIVE);
		data.put("out_rework", false);
		assertNotNull(jbpmTaskService.completeTask(assess.getId(), data));

		assertEquals(assessTask.getCaseInstance().getStatus(), CaseStatus.COMPLETE);
	}

	@Test
	@WithUserDetails("krisv")
	public void getMyTasks() {
		Collection<TaskSummary> tasks = jbpmTaskService.getMyTasks();
		assertNotNull(tasks);
		// assertEquals(1, tasks.size());
	}

	@Test
	@WithUserDetails("krisv")
	public void getGroupTasks() {
		Collection<TaskSummary> tasks = jbpmTaskService.getTasks();
		assertNotNull(tasks);
		// assertEquals(1, tasks.size());
	}

}
