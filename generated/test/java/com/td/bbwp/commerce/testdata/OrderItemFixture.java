
package com.td.bbwp.commerce.testdata;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import org.witchcraft.base.test.BaseFixtureFactory;

import com.td.bbwp.commerce.OrderItem;

//create test data for OrderItem
public class OrderItemFixture extends BaseFixtureFactory<OrderItem> implements TemplateLoader {

	public OrderItemFixture() {
		load();
	}

	@Override
	protected String getName() {
		return "orderItem";
	}

	@Override
	public void load() {

		Fixture.of(OrderItem.class).addTemplate(getName(), new Rule() {
			{
				add("qty", random(Integer.class, range(1, 200)));

				add("product", new com.td.bbwp.commerce.testdata.ProductFixture().getOneRecord());

				add("customerOrder", new com.td.bbwp.commerce.testdata.CustomerOrderFixture().getOneRecord());

			}
		});
	}

	protected Class<OrderItem> getMyClass() {
		return OrderItem.class;
	}

	public static void main(String[] args) {
		new OrderItemFixture().getRecords(5).forEach(System.out::println);
	}
}
