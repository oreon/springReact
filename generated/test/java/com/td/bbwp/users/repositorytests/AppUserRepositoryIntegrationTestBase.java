
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

import com.td.bbwp.users.AppUser;

/**
 * DB tests for {@link  AppUserRepository}
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MainApp.class, SecurityConfiguration.class})
@Transactional
public class AppUserRepositoryIntegrationTestBase {

	@Autowired
	protected com.td.bbwp.web.action.users.AppUserRepository appUserRepository;

	protected com.td.bbwp.users.testdata.AppUserFixture appUserFixture = new com.td.bbwp.users.testdata.AppUserFixture();

	AppUser appUser;

	@Before
	public void setup() throws Exception {

		appUserRepository.deleteAll();

		appUser = appUserFixture.getOneRecord();

		appUser = appUserRepository.save(appUser);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void findOne() {
		assertCount(1L);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void findById() {
		AppUser temp = appUserRepository.findOne(appUser.getId());
		assertThat(temp).isNotNull();
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testEdit() throws IllegalAccessException, InvocationTargetException {
		Long currentId = appUser.getId();
		BeanUtils.copyProperties(appUser, appUserFixture.getOneRecord());
		appUser.setId(currentId);
		appUserRepository.save(appUser);
		assertCount(1L);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testDelete() {
		appUserRepository.delete(appUser);
		assertCount(0L);

	}

	@Test(expected = AccessDeniedException.class)
	@WithMockUser(username = "mrmanager", roles = {"MANAGER"})
	public void testDeleteWithoutPreviliges() {
		appUserRepository.delete(appUser);
		assertCount(0L);
	}

	public void assertCount(long assertCount) {
		Long count = appUserRepository.count();
		assertThat(count).isEqualTo(assertCount);
	}

}
