package com.td.bbwp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.td.bbwp.commerce.Customer;
import com.td.bbwp.commerce.Gender;
import com.td.bbwp.users.AppRole;
import com.td.bbwp.users.AppUser;
import com.td.bbwp.web.action.commerce.CustomerRepository;
import com.td.bbwp.web.action.users.AppRoleRepository;
import com.td.bbwp.web.action.users.AppUserRepository;
import com.td.bbwp.web.action.wf.CaseDefinitionRepository;
import com.td.bbwp.wf.CaseDefinition;
import com.td.bbwp.wf.Field;
import com.td.bbwp.wf.FieldType;
import com.td.bbwp.wf.TaskDefinition;

@SpringBootApplication
/// @EntityScan("com.delivery.domain")
public class MainApp {

	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		// MappingJackson2HttpMessageConverter converter =

		return new MappingJackson2HttpMessageConverter(mapper);
	}

	@Bean
	public CommandLineRunner studentDemo(CaseDefinitionRepository caseDefinitionRepository,
			AppRoleRepository appRoleRepository, AppUserRepository appUserRepository, CustomerRepository cr) {
		return args -> {

			if (appRoleRepository.count() == 0) {
				appRoleRepository.save(new AppRole("admin"));
				appRoleRepository.save(new AppRole("lenders"));
				appRoleRepository.save(new AppRole("adjudicators"));
				appRoleRepository.save(new AppRole("branchAdmin"));
			}

			if (appUserRepository.count() == 0) {
		
				AppUser krs = new AppUser("krisv", "krisv");
				krs.addAppRole(appRoleRepository.findByName("lenders").get());
				krs.addAppRole(appRoleRepository.findByName("adjudicators").get());
				appUserRepository.save(krs); 
				
				AppUser admin = new AppUser("admin", "admin");
				admin.addAppRole(appRoleRepository.findByName("admin").get());
				appUserRepository.save(admin); 
				
				AppUser mary = new AppUser("mary", "mary");
				mary.addAppRole(appRoleRepository.findByName("adjudicators").get());
				appUserRepository.save(mary); 
			}
			
			if (caseDefinitionRepository.count() == 0) {
				CaseDefinition caseDefinition = new CaseDefinition("bb_aam.aam_lending");

				TaskDefinition td = new TaskDefinition("writeup");
				td.addField(new Field("color", FieldType.string, false));
				td.addField(new Field("risk-rating", FieldType.number, true));

				TaskDefinition td2 = new TaskDefinition("assess");

				td2.addField(new Field("recommended_risk_rating", FieldType.number, true));
				td2.addField(new Field("out_rework", FieldType.bool, true));

				caseDefinition.addTaskDefinition(td);
				caseDefinition.addTaskDefinition(td2);

				caseDefinitionRepository.save(caseDefinition);
			}

			if (cr.count() == 0) {
				Customer jab = new Customer();
				jab.setFirstName("jab");
				jab.setLastName("mer");
				jab.setGender(Gender.MALE);
				cr.save(jab);
			}
		};
	}
}
