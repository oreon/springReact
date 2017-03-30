
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

import com.td.bbwp.wf.CaseInstance;

/**
 * DB tests for {@link  CaseInstanceRepository}
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MainApp.class, SecurityConfiguration.class})
@Transactional
public class CaseInstanceRepositoryIntegrationTestBase {

	@Autowired
	protected com.td.bbwp.web.action.wf.CaseInstanceRepository caseInstanceRepository;

	//@Autowired 
	protected com.td.bbwp.wf.testdata.CaseInstanceTestDataFactory caseInstanceTestDataFactory = new com.td.bbwp.wf.testdata.CaseInstanceTestDataFactory();

	@Autowired
	protected com.td.bbwp.web.action.wf.TaskInstanceRepository taskInstanceRepository;

	//@Autowired
	protected com.td.bbwp.wf.testdata.TaskInstanceTestDataFactory taskInstanceTestDataFactory = new com.td.bbwp.wf.testdata.TaskInstanceTestDataFactory();

	CaseInstance caseInstance;

	@Before
	public void setup() throws Exception {

		try {
			caseInstanceRepository.deleteAll();
			caseInstance = caseInstanceTestDataFactory.createCaseInstanceOne();

			caseInstance.addTaskInstance(taskInstanceTestDataFactory.createTaskInstanceOne());

			caseInstanceRepository.save(caseInstance);

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
	public void testDeleteComposedtaskInstances() {
		taskInstanceRepository.delete(caseInstance.getTaskInstances().get(0));
		assertCount(1L);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void findById() {
		CaseInstance temp = caseInstanceRepository.findOne(caseInstance.getId());
		assertThat(temp).isNotNull();
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testEdit() throws IllegalAccessException, InvocationTargetException {
		Long currentId = caseInstance.getId();
		BeanUtils.copyProperties(caseInstance, caseInstanceTestDataFactory.createCaseInstanceTwo());
		caseInstance.setId(currentId);
		caseInstanceRepository.save(caseInstance);
		assertCount(1L);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testDelete() {
		caseInstanceRepository.delete(caseInstance);
		assertCount(0L);

		assertThat(taskInstanceRepository.count() == 0);

	}

	@Test(expected = AccessDeniedException.class)
	@WithMockUser(username = "mrmanager", roles = {"MANAGER"})
	public void testDeleteWithoutPreviliges() {
		caseInstanceRepository.delete(caseInstance);
		assertCount(0L);
	}

	public void assertCount(long assertCount) {
		Long count = caseInstanceRepository.count();
		assertThat(count).isEqualTo(assertCount);
	}

}
