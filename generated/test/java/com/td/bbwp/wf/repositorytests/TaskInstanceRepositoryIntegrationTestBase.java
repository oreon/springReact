
package com.td.bbwp.wf.repositorytests;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.td.bbwp.SecurityConfiguration;
import com.td.bbwp.MainApp;

import com.td.bbwp.wf.TaskInstance;

/**
 * DB tests for {@link  TaskInstanceRepository}
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MainApp.class, SecurityConfiguration.class})
@Transactional
public class TaskInstanceRepositoryIntegrationTestBase {

	@Autowired
	protected com.td.bbwp.web.action.wf.TaskInstanceRepository taskInstanceRepository;

	//@Autowired 
	protected com.td.bbwp.wf.testdata.TaskInstanceTestDataFactory taskInstanceTestDataFactory = new com.td.bbwp.wf.testdata.TaskInstanceTestDataFactory();

	TaskInstance taskInstance;

	@Before
	public void setup() throws Exception {

		try {
			taskInstanceRepository.deleteAll();
			taskInstance = taskInstanceTestDataFactory.createTaskInstanceOne();

			taskInstanceRepository.save(taskInstance);

		} catch (AccessDeniedException ade) {
			System.out.println(ade.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void findOne() {
		assertCount(1L);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void findById() {
		TaskInstance temp = taskInstanceRepository.findOne(taskInstance.getId());
		assertThat(temp).isNotNull();
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testEdit() throws IllegalAccessException, InvocationTargetException {
		Long currentId = taskInstance.getId();
		BeanUtils.copyProperties(taskInstance, taskInstanceTestDataFactory.createTaskInstanceTwo());
		taskInstance.setId(currentId);
		taskInstanceRepository.save(taskInstance);
		assertCount(1L);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testDelete() {
		taskInstanceRepository.delete(taskInstance);
		assertCount(0L);

	}

	@Test(expected = AccessDeniedException.class)
	@WithMockUser(username = "mrmanager", roles = {"MANAGER"})
	public void testDeleteWithoutPreviliges() {
		taskInstanceRepository.delete(taskInstance);
		assertCount(0L);
	}

	public void assertCount(long assertCount) {
		Long count = taskInstanceRepository.count();
		assertThat(count).isEqualTo(assertCount);
	}

}
