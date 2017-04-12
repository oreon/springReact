package com.td.bbwp.process;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.kie.api.task.model.TaskSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.td.bbwp.MainApp;
import com.td.bbwp.course.web.JbpmTaskService;
import com.td.bbwp.course.web.ProcessFacade;
import com.td.bbwp.wf.CaseInstance;
import com.td.bbwp.wf.CaseStatus;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MainApp.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class JbpmTaskServiceTest {

	private final static String NEW_JOURNAL_NAME = "New Journal";

	@Autowired
	private ProcessFacade jbpmTaskService;

	static TaskSummary writeupTask;

	private static boolean initialized = false;

	@Before
	@WithUserDetails("krisv")
	public void launchAaam() {
		if (!initialized) {
			int grpTasksBefore = jbpmTaskService.getTasks().size();
			Collection<TaskSummary> beforeTasks = jbpmTaskService.getTasks();
			CaseInstance caseInstance = jbpmTaskService.launchProcess(JbpmTaskService.BB_AAM_AAM_LENDING, 1L, null);
			assertNotNull(caseInstance);
			assertEquals(caseInstance.getStatus(), CaseStatus.ACTIVE);
			assertEquals(1 + grpTasksBefore, jbpmTaskService.getTasks().size());
			Collection<TaskSummary> newlyCreated = jbpmTaskService.getTasks();
			newlyCreated.removeAll(beforeTasks);
			writeupTask = newlyCreated.iterator().next();
			initialized = true;
		}
	}

	@Test
	@WithUserDetails("krisv")
	public void claimTask() {
		int grpTasksBefore = jbpmTaskService.getTasks().size();
		System.out.println("tyring to claim task " + writeupTask.getId());
		assertNotNull(jbpmTaskService.claimTask(writeupTask.getId()));
		assertEquals( grpTasksBefore - 1, jbpmTaskService.getTasks().size());
		//releaseTask();
	}

	//@Test
	@WithUserDetails("krisv")
	public void startTask() {
		int grpTasksBefore = jbpmTaskService.getTasks().size();
		attemptClaim(writeupTask.getId());
		assertNotNull(jbpmTaskService.startTask(writeupTask.getId()));
	}
	
	//@Test
	@WithUserDetails("krisv")
	public void releaseTask() {
		int grpTasksBefore = jbpmTaskService.getTasks().size();
		attemptClaim(writeupTask.getId());
		assertNotNull(jbpmTaskService.releaseTask(writeupTask.getId()));
		assertEquals(1 + grpTasksBefore, jbpmTaskService.getTasks().size());
	}
	
	public void attemptClaim(long id){
		try{
			jbpmTaskService.claimTask(id);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	@Test
	@WithUserDetails("krisv")
	public void completeTask() {
		int myTasksBefore = jbpmTaskService.getMyTasks().size();
		Map<String, Object> data = new HashMap<>();
		data.put("color", "red");
		data.put("risk_rating", "red");
		attemptClaim(writeupTask.getId());
		jbpmTaskService.startTask(writeupTask.getId());
		assertNotNull(jbpmTaskService.completeTask(writeupTask.getId(), data));
		assertEquals(myTasksBefore -1, jbpmTaskService.getMyTasks().size());
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

	// @Test
	// public void browseUnSubscribedUser() {
	// List<Journal> journals = journalService.listAll(getUser("user2"));
	// assertEquals(0, journals.size());
	// }
	//
	// @Test
	// public void listPublisher() {
	// User user = getUser("publisher1");
	// Optional<Publisher> p = publisherRepository.findByUser(user);
	// List<Journal> journals = journalService.publisherList(p.get());
	// assertEquals(2, journals.size());
	//
	// assertEquals(new Long(1), journals.get(0).getId());
	// assertEquals(new Long(2), journals.get(1).getId());
	//
	// assertEquals("Medicine", journals.get(0).getName());
	// assertEquals("Test Journal", journals.get(1).getName());
	// journals.stream().forEach(j -> assertNotNull(j.getPublishDate()));
	// journals.stream().forEach(j -> assertEquals(new Long(1),
	// j.getPublisher().getId()));
	//
	// }
	//
	// @Test(expected = ServiceException.class)
	// public void publishFail() throws ServiceException {
	// User user = getUser("publisher2");
	// Optional<Publisher> p = publisherRepository.findByUser(user);
	//
	// Journal journal = new Journal();
	// journal.setName("New Journal");
	//
	// journalService.publish(p.get(), journal, 1L);
	// }
	//
	// @Test(expected = ServiceException.class)
	// public void publishFail2() throws ServiceException {
	// User user = getUser("publisher2");
	// Optional<Publisher> p = publisherRepository.findByUser(user);
	//
	// Journal journal = new Journal();
	// journal.setName("New Journal");
	//
	// journalService.publish(p.get(), journal, 150L);
	// }
	//
	// @Test()
	// public void publishSuccess() {
	// User user = getUser("publisher2");
	// Optional<Publisher> p = publisherRepository.findByUser(user);
	//
	// Journal journal = new Journal();
	// journal.setName(NEW_JOURNAL_NAME);
	// journal.setUuid("SOME_EXTERNAL_ID");
	// try {
	// journalService.publish(p.get(), journal, 3L);
	// } catch (ServiceException e) {
	// fail(e.getMessage());
	// }
	//
	// List<Journal> journals = journalService.listAll(getUser("user1"));
	// assertEquals(2, journals.size());
	//
	// journals = journalService.publisherList(p.get());
	// assertEquals(2, journals.size());
	// assertEquals(new Long(3), journals.get(0).getId());
	// assertEquals(new Long(4), journals.get(1).getId());
	// assertEquals("Health", journals.get(0).getName());
	// assertEquals(NEW_JOURNAL_NAME, journals.get(1).getName());
	// journals.stream().forEach(j -> assertNotNull(j.getPublishDate()));
	// journals.stream().forEach(j -> assertEquals(new Long(2),
	// j.getPublisher().getId()));
	// }
	//
	// @Test(expected = ServiceException.class)
	// public void unPublishFail() {
	// User user = getUser("publisher1");
	// Optional<Publisher> p = publisherRepository.findByUser(user);
	// journalService.unPublish(p.get(), 4L);
	// }
	//
	// @Test(expected = ServiceException.class)
	// public void unPublishFail2() {
	// User user = getUser("publisher1");
	// Optional<Publisher> p = publisherRepository.findByUser(user);
	// journalService.unPublish(p.get(), 100L);
	// }
	//
	// @Test
	// public void unPublishSuccess() {
	// User user = getUser("publisher1");
	// Optional<Publisher> p = publisherRepository.findByUser(user);
	// journalService.unPublish(p.get(), 1L);
	//
	// List<Journal> journals = journalService.publisherList(p.get());
	// assertEquals(1, journals.size());
	// journals = journalService.listAll(getUser("user1"));
	// assertEquals(0, journals.size());
	// }
	//

}
