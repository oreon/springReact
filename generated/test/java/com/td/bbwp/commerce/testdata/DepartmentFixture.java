
package com.td.bbwp.commerce.testdata;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import org.witchcraft.base.test.BaseFixtureFactory;

import com.td.bbwp.commerce.Department;

//create test data for Department
public class DepartmentFixture extends BaseFixtureFactory<Department> implements TemplateLoader {

	public DepartmentFixture() {
		load();
	}

	@Override
	protected String getName() {
		return "department";
	}

	@Override
	public void load() {

		Fixture.of(Department.class).addTemplate(getName(), new Rule() {
			{
				add("name", getUniqueNames());

			}
		});
	}

	protected Class<Department> getMyClass() {
		return Department.class;
	}

	public static void main(String[] args) {
		new DepartmentFixture().getRecords(5).forEach(System.out::println);
	}
}
