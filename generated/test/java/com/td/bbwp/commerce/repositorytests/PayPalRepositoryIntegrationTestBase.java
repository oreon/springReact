
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

import com.td.bbwp.commerce.PayPal;

/**
 * DB tests for {@link  PayPalRepository}
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MainApp.class, SecurityConfiguration.class})
@Transactional
public class PayPalRepositoryIntegrationTestBase {

	@Autowired
	protected com.td.bbwp.web.action.commerce.PayPalRepository payPalRepository;

	//@Autowired 
	protected com.td.bbwp.commerce.testdata.PayPalTestDataFactory payPalTestDataFactory = new com.td.bbwp.commerce.testdata.PayPalTestDataFactory();

	PayPal payPal;

	@Before
	public void setup() throws Exception {

		try {
			payPalRepository.deleteAll();
			payPal = payPalTestDataFactory.createPayPalOne();

			payPalRepository.save(payPal);

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
		PayPal temp = payPalRepository.findOne(payPal.getId());
		assertThat(temp).isNotNull();
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testEdit() throws IllegalAccessException, InvocationTargetException {
		Long currentId = payPal.getId();
		BeanUtils.copyProperties(payPal, payPalTestDataFactory.createPayPalTwo());
		payPal.setId(currentId);
		payPalRepository.save(payPal);
		assertCount(1L);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testDelete() {
		payPalRepository.delete(payPal);
		assertCount(0L);

	}

	@Test(expected = AccessDeniedException.class)
	@WithMockUser(username = "mrmanager", roles = {"MANAGER"})
	public void testDeleteWithoutPreviliges() {
		payPalRepository.delete(payPal);
		assertCount(0L);
	}

	public void assertCount(long assertCount) {
		Long count = payPalRepository.count();
		assertThat(count).isEqualTo(assertCount);
	}

}
