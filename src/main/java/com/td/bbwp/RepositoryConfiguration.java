package com.td.bbwp;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.td.bbwp.commerce.Customer;
import com.td.bbwp.course.domain.Student;
import com.td.bbwp.course.domain.User;
import com.td.bbwp.wf.CaseDefinition;

@Configuration
public class RepositoryConfiguration extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(User.class);
        config.exposeIdsFor(Student.class);
        config.exposeIdsFor(Customer.class);
        config.exposeIdsFor(CaseDefinition.class);
    }
}