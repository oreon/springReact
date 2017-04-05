package fi.haagahelia.course;

import static org.junit.Assert.*;
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
import com.td.bbwp.service.wf.CaseDefinitionService;
import com.td.bbwp.wf.CaseDefinition;
import com.td.bbwp.wf.TaskDefinition;

/**
 * Created by jsingh on 2017-02-12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MainApp.class)
@WebAppConfiguration
@Transactional
public class CaseDefinitionControllerTest {

	private MockMvc mockMvc;
	ObjectMapper ojbectMapper = new ObjectMapper();
	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Autowired
	CaseDefinitionService caseDefinitionService;

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
	public void testReadCaseDefintionById() throws Exception {
		mockMvc.perform(get("/rest/caseDefinitions/1")).andExpect(status().isOk())
				// .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.name").value("bb_aam.aam_lending"));
	}
	
	@Test
	@WithUserDetails("admin")
	public void testReadCaseDefintions() throws Exception {
		mockMvc.perform(get("/rest/caseDefinitions/")).andExpect(status().isOk())
				// .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.[0].name").value("bb_aam.aam_lending"));
	}

	@Test
	@WithUserDetails("krisv")
	public void testDeleteCaseDefintionByIdNotAllowed() throws Exception {
		mockMvc.perform(delete("/rest/caseDefinitions/1")).andExpect(status().isOk());
	}

	@Test
	@WithUserDetails("admin")
	public void testReadCaseDefintionByIdNF() throws Exception {
		mockMvc.perform(get("/rest/caseDefinitions/6889")).andExpect(status().isNotFound());
	}
	
	

	@Test
	@WithUserDetails("admin")
	public void testCreationOfCaseDefintion() throws Exception {
		CaseDefinition caseDefinition = new CaseDefinition();
		caseDefinition.setName("MyName");
		TaskDefinition tdf = new TaskDefinition();
		tdf.setName("one task");
		caseDefinition.addTaskDefinition(tdf);
		
		System.out.println(json(caseDefinition));

		this.mockMvc.perform(post("/rest/caseDefinitions").contentType(contentType).content(json(caseDefinition)))
				.andExpect(status().isCreated());

	}

	@Test
	@WithUserDetails("admin")
	public void testEditOfCaseDefintion() throws Exception {

		CaseDefinition caseDef = caseDefinitionService.findOne(1L).map(x -> x)
				.orElseThrow(() -> new RuntimeException("Record not found"));
		
		long records = caseDefinitionService.count();

		this.mockMvc.perform(put("/rest/caseDefinitions/1").contentType(contentType).content(json(caseDef)))
				.andExpect(status().isOk());
		
		assertEquals(records, caseDefinitionService.count());
	}

	// @Test
	// @WithUserDetails("admin")
	public void testCreationOfANewProjectNA() throws Exception {
		CaseDefinition caseDefinition = new CaseDefinition();
		caseDefinition.setName("MyName");

		mockMvc.perform(post("/rest/caseDefinitions/").accept(MediaType.APPLICATION_JSON)
				// .contentType(MediaType.APPLICATION_JSON)
				.content(ojbectMapper.writeValueAsString(caseDefinition))).andExpect(status().isMethodNotAllowed());
	}

}