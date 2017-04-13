
package com.td.bbwp.commerce.testdata;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import org.witchcraft.base.test.BaseFixtureFactory;

import com.td.bbwp.commerce.CustomerOrder;

//create test data for CustomerOrder
public class CustomerOrderFixture extends BaseFixtureFactory<CustomerOrder> implements TemplateLoader {

	public CustomerOrderFixture() {
		load();
	}

	@Override
	protected String getName() {
		return "customerOrder";
	}

	@Override
	public void load() {

		Fixture.of(CustomerOrder.class).addTemplate(getName(), new Rule() {
			{
				add("notes", random(getUniqueNames()));
				add("shipDate", randomDate("2011-04-15", "2011-11-07", new SimpleDateFormat("yyyy-MM-dd")));

				add("customer", new com.td.bbwp.commerce.testdata.CustomerFixture().getOneRecord());

				add("paymentMethod", new com.td.bbwp.commerce.testdata.PaymentMethodFixture().getOneRecord());

			}
		});
	}

	protected Class<CustomerOrder> getMyClass() {
		return CustomerOrder.class;
	}

	public static void main(String[] args) {
		new CustomerOrderFixture().getRecords(5).forEach(System.out::println);
	}
}
