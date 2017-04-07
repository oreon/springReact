
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

import com.td.bbwp.commerce.Customer;

/**
 * DB tests for {@link  CustomerRepository}
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MainApp.class, SecurityConfiguration.class})
@Transactional
public class CustomerRepositoryIntegrationTestBase {

	@Autowired
	protected com.td.bbwp.web.action.commerce.CustomerRepository customerRepository;

	protected com.td.bbwp.commerce.testdata.CustomerFixture customerFixture = new com.td.bbwp.commerce.testdata.CustomerFixture();

	Customer customer;

	@Before
	public void setup() throws Exception {

		customerRepository.deleteAll();

		customer = customerFixture.getOneRecord();

		customer = customerRepository.save(customer);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void findOne() {
		assertCount(1L);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void findById() {
		Customer temp = customerRepository.findOne(customer.getId());
		assertThat(temp).isNotNull();
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testEdit() throws IllegalAccessException, InvocationTargetException {
		Long currentId = customer.getId();
		BeanUtils.copyProperties(customer, customerFixture.getOneRecord());
		customer.setId(currentId);
		customerRepository.save(customer);
		assertCount(1L);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testDelete() {
		customerRepository.delete(customer);
		assertCount(0L);

	}

	@Test(expected = AccessDeniedException.class)
	@WithMockUser(username = "mrmanager", roles = {"MANAGER"})
	public void testDeleteWithoutPreviliges() {
		customerRepository.delete(customer);
		assertCount(0L);
	}

	public void assertCount(long assertCount) {
		Long count = customerRepository.count();
		assertThat(count).isEqualTo(assertCount);
	}

}
