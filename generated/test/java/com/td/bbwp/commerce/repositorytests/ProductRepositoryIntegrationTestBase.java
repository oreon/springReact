
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

import com.td.bbwp.commerce.Product;

/**
 * DB tests for {@link  ProductRepository}
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MainApp.class, SecurityConfiguration.class})
@Transactional
public class ProductRepositoryIntegrationTestBase {

	@Autowired
	protected com.td.bbwp.web.action.commerce.ProductRepository productRepository;

	//@Autowired 
	protected com.td.bbwp.commerce.testdata.ProductTestDataFactory productTestDataFactory = new com.td.bbwp.commerce.testdata.ProductTestDataFactory();

	Product product;

	@Before
	public void setup() throws Exception {

		try {
			productRepository.deleteAll();
			product = productTestDataFactory.createProductOne();

			productRepository.save(product);

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
		Product temp = productRepository.findOne(product.getId());
		assertThat(temp).isNotNull();
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testEdit() throws IllegalAccessException, InvocationTargetException {
		Long currentId = product.getId();
		BeanUtils.copyProperties(product, productTestDataFactory.createProductTwo());
		product.setId(currentId);
		productRepository.save(product);
		assertCount(1L);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testDelete() {
		productRepository.delete(product);
		assertCount(0L);

	}

	@Test(expected = AccessDeniedException.class)
	@WithMockUser(username = "mrmanager", roles = {"MANAGER"})
	public void testDeleteWithoutPreviliges() {
		productRepository.delete(product);
		assertCount(0L);
	}

	public void assertCount(long assertCount) {
		Long count = productRepository.count();
		assertThat(count).isEqualTo(assertCount);
	}

}
