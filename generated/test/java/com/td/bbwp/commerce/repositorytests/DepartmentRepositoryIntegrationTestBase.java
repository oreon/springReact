
package com.td.bbwp.commerce.repositorytests;

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

import com.td.bbwp.commerce.Department;

/**
 * DB tests for {@link  DepartmentRepository}
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MainApp.class, SecurityConfiguration.class})
@Transactional
public class DepartmentRepositoryIntegrationTestBase {

	@Autowired
	protected com.td.bbwp.web.action.commerce.DepartmentRepository departmentRepository;

	protected com.td.bbwp.commerce.testdata.DepartmentFixture departmentFixture = new com.td.bbwp.commerce.testdata.DepartmentFixture();

	Department department;

	@Before
	public void setup() throws Exception {

		departmentRepository.deleteAll();

		department = departmentFixture.getOneRecord();

		department = departmentRepository.save(department);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void findOne() {
		assertCount(1L);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void findById() {
		Department temp = departmentRepository.findOne(department.getId());
		assertThat(temp).isNotNull();
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testEdit() throws IllegalAccessException, InvocationTargetException {
		Long currentId = department.getId();
		BeanUtils.copyProperties(department, departmentFixture.getOneRecord());
		department.setId(currentId);
		departmentRepository.save(department);
		assertCount(1L);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testDelete() {
		departmentRepository.delete(department);
		assertCount(0L);

	}

	@Test(expected = AccessDeniedException.class)
	@WithMockUser(username = "mrmanager", roles = {"MANAGER"})
	public void testDeleteWithoutPreviliges() {
		departmentRepository.delete(department);
		assertCount(0L);
	}

	public void assertCount(long assertCount) {
		Long count = departmentRepository.count();
		assertThat(count).isEqualTo(assertCount);
	}

}
