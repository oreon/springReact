
package com.td.bbwp.users.repositorytests;

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

import com.td.bbwp.users.AppRole;

/**
 * DB tests for {@link  AppRoleRepository}
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MainApp.class, SecurityConfiguration.class})
@Transactional
public class AppRoleRepositoryIntegrationTestBase {

	@Autowired
	protected com.td.bbwp.web.action.users.AppRoleRepository appRoleRepository;

	protected com.td.bbwp.users.testdata.AppRoleFixture appRoleFixture = new com.td.bbwp.users.testdata.AppRoleFixture();

	AppRole appRole;

	@Before
	public void setup() throws Exception {

		appRoleRepository.deleteAll();

		appRole = appRoleFixture.getOneRecord();

		appRole = appRoleRepository.save(appRole);
	}

	//@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void findOne() {
		assertCount(1L);
	}

	//@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void findById() {
		AppRole temp = appRoleRepository.findOne(appRole.getId());
		assertThat(temp).isNotNull();
	}

	//@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testEdit() throws IllegalAccessException, InvocationTargetException {
		Long currentId = appRole.getId();
		BeanUtils.copyProperties(appRole, appRoleFixture.getOneRecord());
		appRole.setId(currentId);
		appRoleRepository.save(appRole);
		assertCount(1L);
	}

	//@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testDelete() {
		appRoleRepository.delete(appRole);
		assertCount(0L);

	}

	@Test(expected = AccessDeniedException.class)
	@WithMockUser(username = "mrmanager", roles = {"MANAGER"})
	public void testDeleteWithoutPreviliges() {
		appRoleRepository.delete(appRole);
		assertCount(0L);
	}

	public void assertCount(long assertCount) {
		Long count = appRoleRepository.count();
		assertThat(count).isEqualTo(assertCount);
	}

}
