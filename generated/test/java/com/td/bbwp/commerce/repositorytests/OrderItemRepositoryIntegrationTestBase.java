
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

import com.td.bbwp.commerce.OrderItem;

/**
 * DB tests for {@link  OrderItemRepository}
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MainApp.class, SecurityConfiguration.class})
@Transactional
public class OrderItemRepositoryIntegrationTestBase {

	@Autowired
	protected com.td.bbwp.web.action.commerce.OrderItemRepository orderItemRepository;

	protected com.td.bbwp.commerce.testdata.OrderItemFixture orderItemFixture = new com.td.bbwp.commerce.testdata.OrderItemFixture();

	OrderItem orderItem;

	@Before
	public void setup() throws Exception {

		orderItemRepository.deleteAll();

		orderItem = orderItemFixture.getOneRecord();

		orderItem = orderItemRepository.save(orderItem);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void findOne() {
		assertCount(1L);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void findById() {
		OrderItem temp = orderItemRepository.findOne(orderItem.getId());
		assertThat(temp).isNotNull();
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testEdit() throws IllegalAccessException, InvocationTargetException {
		Long currentId = orderItem.getId();
		BeanUtils.copyProperties(orderItem, orderItemFixture.getOneRecord());
		orderItem.setId(currentId);
		orderItemRepository.save(orderItem);
		assertCount(1L);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testDelete() {
		orderItemRepository.delete(orderItem);
		assertCount(0L);

	}

	@Test(expected = AccessDeniedException.class)
	@WithMockUser(username = "mrmanager", roles = {"MANAGER"})
	public void testDeleteWithoutPreviliges() {
		orderItemRepository.delete(orderItem);
		assertCount(0L);
	}

	public void assertCount(long assertCount) {
		Long count = orderItemRepository.count();
		assertThat(count).isEqualTo(assertCount);
	}

}
