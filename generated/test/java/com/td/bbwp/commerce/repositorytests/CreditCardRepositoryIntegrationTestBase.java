
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

import com.td.bbwp.commerce.CreditCard;

/**
 * DB tests for {@link  CreditCardRepository}
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MainApp.class, SecurityConfiguration.class})
@Transactional
public class CreditCardRepositoryIntegrationTestBase {

	@Autowired
	protected com.td.bbwp.web.action.commerce.CreditCardRepository creditCardRepository;

	//@Autowired 
	protected com.td.bbwp.commerce.testdata.CreditCardTestDataFactory creditCardTestDataFactory = new com.td.bbwp.commerce.testdata.CreditCardTestDataFactory();

	CreditCard creditCard;

	@Before
	public void setup() throws Exception {

		try {
			creditCardRepository.deleteAll();
			creditCard = creditCardTestDataFactory.createCreditCardOne();

			creditCardRepository.save(creditCard);

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
		CreditCard temp = creditCardRepository.findOne(creditCard.getId());
		assertThat(temp).isNotNull();
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testEdit() throws IllegalAccessException, InvocationTargetException {
		Long currentId = creditCard.getId();
		BeanUtils.copyProperties(creditCard, creditCardTestDataFactory.createCreditCardTwo());
		creditCard.setId(currentId);
		creditCardRepository.save(creditCard);
		assertCount(1L);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testDelete() {
		creditCardRepository.delete(creditCard);
		assertCount(0L);

	}

	@Test(expected = AccessDeniedException.class)
	@WithMockUser(username = "mrmanager", roles = {"MANAGER"})
	public void testDeleteWithoutPreviliges() {
		creditCardRepository.delete(creditCard);
		assertCount(0L);
	}

	public void assertCount(long assertCount) {
		Long count = creditCardRepository.count();
		assertThat(count).isEqualTo(assertCount);
	}

}
