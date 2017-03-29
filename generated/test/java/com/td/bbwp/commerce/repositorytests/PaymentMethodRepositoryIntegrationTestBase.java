
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

import com.td.bbwp.commerce.PaymentMethod;

/**
 * DB tests for {@link  PaymentMethodRepository}
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MainApp.class, SecurityConfiguration.class})
@Transactional
public class PaymentMethodRepositoryIntegrationTestBase {

	@Autowired
	protected com.td.bbwp.web.action.commerce.PaymentMethodRepository paymentMethodRepository;

	//@Autowired 
	protected com.td.bbwp.commerce.testdata.PaymentMethodTestDataFactory paymentMethodTestDataFactory = new com.td.bbwp.commerce.testdata.PaymentMethodTestDataFactory();

	PaymentMethod paymentMethod;

	@Before
	public void setup() throws Exception {

		try {
			paymentMethodRepository.deleteAll();
			paymentMethod = paymentMethodTestDataFactory.createPaymentMethodOne();

			paymentMethodRepository.save(paymentMethod);

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
		PaymentMethod temp = paymentMethodRepository.findOne(paymentMethod.getId());
		assertThat(temp).isNotNull();
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testEdit() throws IllegalAccessException, InvocationTargetException {
		Long currentId = paymentMethod.getId();
		BeanUtils.copyProperties(paymentMethod, paymentMethodTestDataFactory.createPaymentMethodTwo());
		paymentMethod.setId(currentId);
		paymentMethodRepository.save(paymentMethod);
		assertCount(1L);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testDelete() {
		paymentMethodRepository.delete(paymentMethod);
		assertCount(0L);

	}

	@Test(expected = AccessDeniedException.class)
	@WithMockUser(username = "mrmanager", roles = {"MANAGER"})
	public void testDeleteWithoutPreviliges() {
		paymentMethodRepository.delete(paymentMethod);
		assertCount(0L);
	}

	public void assertCount(long assertCount) {
		Long count = paymentMethodRepository.count();
		assertThat(count).isEqualTo(assertCount);
	}

}
