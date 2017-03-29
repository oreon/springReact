
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

import com.td.bbwp.wf.CaseDefinition;

/**
 * DB tests for {@link  CaseDefinitionRepository}
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MainApp.class, SecurityConfiguration.class})
@Transactional
public class CaseDefinitionRepositoryIntegrationTestBase {

	@Autowired
	protected com.td.bbwp.web.action.wf.CaseDefinitionRepository caseDefinitionRepository;

	//@Autowired 
	protected com.td.bbwp.wf.testdata.CaseDefinitionTestDataFactory caseDefinitionTestDataFactory = new com.td.bbwp.wf.testdata.CaseDefinitionTestDataFactory();

	@Autowired
	protected com.td.bbwp.web.action.wf.TaskDefinitionRepository taskDefinitionRepository;

	//@Autowired
	protected com.td.bbwp.wf.testdata.TaskDefinitionTestDataFactory taskDefinitionTestDataFactory = new com.td.bbwp.wf.testdata.TaskDefinitionTestDataFactory();

	CaseDefinition caseDefinition;

	@Before
	public void setup() throws Exception {

		try {
			caseDefinitionRepository.deleteAll();
			caseDefinition = caseDefinitionTestDataFactory.createCaseDefinitionOne();

			caseDefinition.addTaskDefinition(taskDefinitionTestDataFactory.createTaskDefinitionOne());

			caseDefinitionRepository.save(caseDefinition);

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
	public void testDeleteComposedtaskDefinitions() {
		taskDefinitionRepository.delete(caseDefinition.getTaskDefinitions().get(0));
		assertCount(1L);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void findById() {
		CaseDefinition temp = caseDefinitionRepository.findOne(caseDefinition.getId());
		assertThat(temp).isNotNull();
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testEdit() throws IllegalAccessException, InvocationTargetException {
		Long currentId = caseDefinition.getId();
		BeanUtils.copyProperties(caseDefinition, caseDefinitionTestDataFactory.createCaseDefinitionTwo());
		caseDefinition.setId(currentId);
		caseDefinitionRepository.save(caseDefinition);
		assertCount(1L);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testDelete() {
		caseDefinitionRepository.delete(caseDefinition);
		assertCount(0L);

		assertThat(taskDefinitionRepository.count() == 0);

	}

	@Test(expected = AccessDeniedException.class)
	@WithMockUser(username = "mrmanager", roles = {"MANAGER"})
	public void testDeleteWithoutPreviliges() {
		caseDefinitionRepository.delete(caseDefinition);
		assertCount(0L);
	}

	public void assertCount(long assertCount) {
		Long count = caseDefinitionRepository.count();
		assertThat(count).isEqualTo(assertCount);
	}

}
