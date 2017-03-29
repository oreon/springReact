
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

	//@Autowired 
	protected com.td.bbwp.wf.testdata.FieldTestDataFactory fieldTestDataFactory = new com.td.bbwp.wf.testdata.FieldTestDataFactory();

	Field field;

	@Before
	public void setup() throws Exception {

		try {
			fieldRepository.deleteAll();
			field = fieldTestDataFactory.createFieldOne();

			fieldRepository.save(field);

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
		Field temp = fieldRepository.findOne(field.getId());
		assertThat(temp).isNotNull();
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testEdit() throws IllegalAccessException, InvocationTargetException {
		Long currentId = field.getId();
		BeanUtils.copyProperties(field, fieldTestDataFactory.createFieldTwo());
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
