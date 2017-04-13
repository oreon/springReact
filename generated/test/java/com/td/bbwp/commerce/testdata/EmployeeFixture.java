
package com.td.bbwp.commerce.testdata;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import org.witchcraft.base.test.BaseFixtureFactory;

import com.td.bbwp.commerce.Employee;

//create test data for Employee
public class EmployeeFixture extends BaseFixtureFactory<Employee> implements TemplateLoader {

	public EmployeeFixture() {
		load();
	}

	@Override
	protected String getName() {
		return "employee";
	}

	@Override
	public void load() {

		Fixture.of(Employee.class).addTemplate(getName(), new Rule() {
			{
				add("gender", random(com.td.bbwp.commerce.Gender.class));
				add("dob", randomDate("2011-04-15", "2011-11-07", new SimpleDateFormat("yyyy-MM-dd")));
				add("firstName", random(getUniqueNames()));
				add("lastName", random(getUniqueNames()));
				add("code", random(getUniqueNames()));

				add("department", new com.td.bbwp.commerce.testdata.DepartmentFixture().getOneRecord());

			}
		});
	}

	protected Class<Employee> getMyClass() {
		return Employee.class;
	}

	public static void main(String[] args) {
		new EmployeeFixture().getRecords(5).forEach(System.out::println);
	}
}
