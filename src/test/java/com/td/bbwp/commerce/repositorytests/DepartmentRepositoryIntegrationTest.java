
package com.td.bbwp.commerce.repositorytests;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.td.bbwp.MainApp;
import com.td.bbwp.SecurityConfiguration;
import com.td.bbwp.web.action.commerce.CustomerRepository;

/**
 * DB tests for {@link CustomerRepository}.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MainApp.class, SecurityConfiguration.class})
@Transactional
public class DepartmentRepositoryIntegrationTest extends DepartmentRepositoryIntegrationTestBase {

}
