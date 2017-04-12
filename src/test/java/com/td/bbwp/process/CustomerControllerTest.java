package com.td.bbwp.process;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.td.bbwp.MainApp;
import com.td.bbwp.commerce.Customer;
import com.td.bbwp.service.commerce.CustomerService;
import com.td.bbwp.wf.TaskDefinition;

/**
 * Created by jsingh on 2017-02-12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MainApp.class)
@WebAppConfiguration
@Transactional
public class CustomerControllerTest {

	private MockMvc mockMvc;
	ObjectMapper ojbectMapper = new ObjectMapper();
	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Autowired
	CustomerService customerService;

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().orElse(null);

		assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
	}

	protected String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
	}

	@Test
	@WithUserDetails("admin")
	public void testReadCustomerById() throws Exception {
		mockMvc.perform(get("/rest/customers/1")).andExpect(status().isOk())
				// .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.firstName").value("jab"));
	}

	@Test
	@WithUserDetails("admin")
	public void testReadCustomers() throws Exception {
		mockMvc.perform(get("/rest/customers/")).andExpect(status().isOk())
				// .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.[0].firstName").value("jab"));
	}

	@Test
	@WithUserDetails("krisv")
	public void testDeleteCustomerByIdNotAllowed() throws Exception {
		mockMvc.perform(delete("/rest/customers/1")).andExpect(status().isOk());
	}

	@Test
	@WithUserDetails("admin")
	public void testReadCustomerByIdNF() throws Exception {
		mockMvc.perform(get("/rest/customers/6889")).andExpect(status().isNotFound());
	}

	@Test
	@WithUserDetails("krisv")
	public void testCreationOfCustomer() throws Exception {
		Customer customer = new Customer();

		System.out.println(json(customer));

		this.mockMvc.perform(post("/rest/customers").contentType(contentType).content(json(customer)))
				.andExpect(status().isCreated());
	}

	@Test
	@WithUserDetails("krisv")
	public void testEditOfCustomer() throws Exception {

		Customer caseDef = customerService.findOne(1L).map(x -> x)
				.orElseThrow(() -> new RuntimeException("Record not found"));

		long records = customerService.count();

		this.mockMvc.perform(put("/rest/customers/1").contentType(contentType).content(json(caseDef)))
				.andExpect(status().isOk());

		assertEquals(records, customerService.count());
	}

	//@Test
	@WithUserDetails("krisv")
	public void testCreationOfANewProjectNA() throws Exception {

		mockMvc.perform(delete("/rest/customers/1").accept(MediaType.APPLICATION_JSON)
				 .contentType(MediaType.APPLICATION_JSON)
				.content("xxx"))
				.andExpect(status().isMethodNotAllowed());
	}

}