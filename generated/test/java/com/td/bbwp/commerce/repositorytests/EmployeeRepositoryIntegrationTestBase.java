
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

import com.td.bbwp.commerce.Employee;

/**
 * DB tests for {@link  EmployeeRepository}
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MainApp.class, SecurityConfiguration.class})
@Transactional
public class EmployeeRepositoryIntegrationTestBase {

	@Autowired
	protected com.td.bbwp.web.action.commerce.EmployeeRepository employeeRepository;

	protected com.td.bbwp.commerce.testdata.EmployeeFixture employeeFixture = new com.td.bbwp.commerce.testdata.EmployeeFixture();

	Employee employee;

	@Before
	public void setup() throws Exception {

		employeeRepository.deleteAll();

		employee = employeeFixture.getOneRecord();

		employee = employeeRepository.save(employee);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void findOne() {
		assertCount(1L);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void findById() {
		Employee temp = employeeRepository.findOne(employee.getId());
		assertThat(temp).isNotNull();
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testEdit() throws IllegalAccessException, InvocationTargetException {
		Long currentId = employee.getId();
		BeanUtils.copyProperties(employee, employeeFixture.getOneRecord());
		employee.setId(currentId);
		employeeRepository.save(employee);
		assertCount(1L);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testDelete() {
		employeeRepository.delete(employee);
		assertCount(0L);

	}

	@Test(expected = AccessDeniedException.class)
	@WithMockUser(username = "mrmanager", roles = {"MANAGER"})
	public void testDeleteWithoutPreviliges() {
		employeeRepository.delete(employee);
		assertCount(0L);
	}

	public void assertCount(long assertCount) {
		Long count = employeeRepository.count();
		assertThat(count).isEqualTo(assertCount);
	}

}
