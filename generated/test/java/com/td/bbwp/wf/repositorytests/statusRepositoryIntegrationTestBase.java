
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

import com.td.bbwp.wf.status;

/**
 * DB tests for {@link  statusRepository}
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MainApp.class, SecurityConfiguration.class})
@Transactional
public class statusRepositoryIntegrationTestBase {

	@Autowired
	protected com.td.bbwp.web.action.wf.statusRepository statusRepository;

	//@Autowired 
	protected com.td.bbwp.wf.testdata.statusTestDataFactory statusTestDataFactory = new com.td.bbwp.wf.testdata.statusTestDataFactory();

	status status;

	@Before
	public void setup() throws Exception {

		try {
			statusRepository.deleteAll();
			status = statusTestDataFactory.createstatusOne();

			statusRepository.save(status);

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
		status temp = statusRepository.findOne(status.getId());
		assertThat(temp).isNotNull();
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testEdit() throws IllegalAccessException, InvocationTargetException {
		Long currentId = status.getId();
		BeanUtils.copyProperties(status, statusTestDataFactory.createstatusTwo());
		status.setId(currentId);
		statusRepository.save(status);
		assertCount(1L);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testDelete() {
		statusRepository.delete(status);
		assertCount(0L);

	}

	@Test(expected = AccessDeniedException.class)
	@WithMockUser(username = "mrmanager", roles = {"MANAGER"})
	public void testDeleteWithoutPreviliges() {
		statusRepository.delete(status);
		assertCount(0L);
	}

	public void assertCount(long assertCount) {
		Long count = statusRepository.count();
		assertThat(count).isEqualTo(assertCount);
	}

}
