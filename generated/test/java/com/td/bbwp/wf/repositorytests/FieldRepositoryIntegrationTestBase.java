
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

import com.td.bbwp.wf.Field;

/**
 * DB tests for {@link  FieldRepository}
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MainApp.class, SecurityConfiguration.class})
@Transactional
public class FieldRepositoryIntegrationTestBase {

	@Autowired
	protected com.td.bbwp.web.action.wf.FieldRepository fieldRepository;

	protected com.td.bbwp.wf.testdata.FieldFixture fieldFixture = new com.td.bbwp.wf.testdata.FieldFixture();

	Field field;

	@Before
	public void setup() throws Exception {

		fieldRepository.deleteAll();

		field = fieldFixture.getOneRecord();

		field = fieldRepository.save(field);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void findOne() {
		assertCount(1L);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void findById() {
		Field temp = fieldRepository.findOne(field.getId());
		assertThat(temp).isNotNull();
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testEdit() throws IllegalAccessException, InvocationTargetException {
		Long currentId = field.getId();
		BeanUtils.copyProperties(field, fieldFixture.getOneRecord());
		field.setId(currentId);
		fieldRepository.save(field);
		assertCount(1L);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testDelete() {
		fieldRepository.delete(field);
		assertCount(0L);

	}

	@Test(expected = AccessDeniedException.class)
	@WithMockUser(username = "mrmanager", roles = {"MANAGER"})
	public void testDeleteWithoutPreviliges() {
		fieldRepository.delete(field);
		assertCount(0L);
	}

	public void assertCount(long assertCount) {
		Long count = fieldRepository.count();
		assertThat(count).isEqualTo(assertCount);
	}

}
