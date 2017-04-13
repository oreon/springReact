
package com.td.bbwp.commerce.testdata;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import org.witchcraft.base.test.BaseFixtureFactory;

import com.td.bbwp.commerce.Customer;

//create test data for Customer
public class CustomerFixture extends BaseFixtureFactory<Customer> implements TemplateLoader {

	public CustomerFixture() {
		load();
	}

	@Override
	protected String getName() {
		return "customer";
	}

	@Override
	public void load() {

		Fixture.of(Customer.class).addTemplate(getName(), new Rule() {
			{
				add("gender", random(com.td.bbwp.commerce.Gender.class));
				add("dob", randomDate("2011-04-15", "2011-11-07", new SimpleDateFormat("yyyy-MM-dd")));
				add("firstName", random(getUniqueNames()));
				add("lastName", random(getUniqueNames()));

			}
		});
	}

	protected Class<Customer> getMyClass() {
		return Customer.class;
	}

	public static void main(String[] args) {
		new CustomerFixture().getRecords(5).forEach(System.out::println);
	}
}
