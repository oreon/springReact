package fi.haagahelia.course;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.td.bbwp.MainApp;
import com.td.bbwp.wf.CaseDefinition;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


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


    @Autowired
    private WebApplicationContext webApplicationContext;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithUserDetails("admin")
    public void testReadCaseDefintionById() throws Exception {
        mockMvc.perform(get("/rest/caseDefinitions/1"))
                .andExpect(status().isOk())
                //.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.name").value("aam_lending"));
    }
    
//    @Test
//    @WithUserDetails("admin2")
//    public void testReadCaseDefintionByIdNotAllowed() throws Exception {
//        mockMvc.perform(get("/rest/caseDefinitions/1"))
//                .andExpect(status().isMethodNotAllowed());
//    }
    
    @Test
    @WithUserDetails("admin")
    public void testReadCaseDefintionByIdNF() throws Exception {
        mockMvc.perform(get("/rest/caseDefinitions/6"))
                .andExpect(status().isNotFound());
    }
    
    @Test
    public void testCreationOfANewProjectSucceeds() throws Exception {
        CaseDefinition caseDefinition = new CaseDefinition();
        caseDefinition.setName("MyName");
  
        mockMvc.perform(
                post("/rest/caseDefintions")
                        .accept(MediaType.APPLICATION_JSON)
                     //   .contentType(MediaType.APPLICATION_JSON)
                        .content(ojbectMapper.writeValueAsString(caseDefinition)))
                .andExpect(status().isCreated());
    }

    
    
    
    
}