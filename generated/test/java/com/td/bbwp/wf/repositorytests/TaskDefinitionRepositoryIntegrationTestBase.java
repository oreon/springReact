
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

import com.td.bbwp.wf.TaskDefinition;

/**
 * DB tests for {@link  TaskDefinitionRepository}
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MainApp.class, SecurityConfiguration.class})
@Transactional
public class TaskDefinitionRepositoryIntegrationTestBase {

	@Autowired
	protected com.td.bbwp.web.action.wf.TaskDefinitionRepository taskDefinitionRepository;

	//@Autowired 
	protected com.td.bbwp.wf.testdata.TaskDefinitionTestDataFactory taskDefinitionTestDataFactory = new com.td.bbwp.wf.testdata.TaskDefinitionTestDataFactory();

	@Autowired
	protected com.td.bbwp.web.action.wf.FieldRepository fieldRepository;

	//@Autowired
	protected com.td.bbwp.wf.testdata.FieldTestDataFactory fieldTestDataFactory = new com.td.bbwp.wf.testdata.FieldTestDataFactory();

	TaskDefinition taskDefinition;

	@Before
	public void setup() throws Exception {

		try {
			taskDefinitionRepository.deleteAll();
			taskDefinition = taskDefinitionTestDataFactory.createTaskDefinitionOne();

			taskDefinition.addField(fieldTestDataFactory.createFieldOne());

			taskDefinitionRepository.save(taskDefinition);

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
	public void testDeleteComposedfields() {
		fieldRepository.delete(taskDefinition.getFields().get(0));
		assertCount(1L);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void findById() {
		TaskDefinition temp = taskDefinitionRepository.findOne(taskDefinition.getId());
		assertThat(temp).isNotNull();
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testEdit() throws IllegalAccessException, InvocationTargetException {
		Long currentId = taskDefinition.getId();
		BeanUtils.copyProperties(taskDefinition, taskDefinitionTestDataFactory.createTaskDefinitionTwo());
		taskDefinition.setId(currentId);
		taskDefinitionRepository.save(taskDefinition);
		assertCount(1L);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testDelete() {
		taskDefinitionRepository.delete(taskDefinition);
		assertCount(0L);

		assertThat(fieldRepository.count() == 0);

	}

	@Test(expected = AccessDeniedException.class)
	@WithMockUser(username = "mrmanager", roles = {"MANAGER"})
	public void testDeleteWithoutPreviliges() {
		taskDefinitionRepository.delete(taskDefinition);
		assertCount(0L);
	}

	public void assertCount(long assertCount) {
		Long count = taskDefinitionRepository.count();
		assertThat(count).isEqualTo(assertCount);
	}

}
