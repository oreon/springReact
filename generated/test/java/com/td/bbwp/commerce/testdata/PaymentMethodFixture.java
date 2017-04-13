
package com.td.bbwp.commerce.testdata;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import org.witchcraft.base.test.BaseFixtureFactory;

import com.td.bbwp.commerce.PaymentMethod;

//create test data for PaymentMethod
public class PaymentMethodFixture extends BaseFixtureFactory<PaymentMethod> implements TemplateLoader {

	public PaymentMethodFixture() {
		load();
	}

	@Override
	protected String getName() {
		return "paymentMethod";
	}

	@Override
	public void load() {

		Fixture.of(PaymentMethod.class).addTemplate(getName(), new Rule() {
			{
				add("accountAddress", random(getUniqueNames()));

			}
		});
	}

	protected Class<PaymentMethod> getMyClass() {
		return PaymentMethod.class;
	}

	public static void main(String[] args) {
		new PaymentMethodFixture().getRecords(5).forEach(System.out::println);
	}
}
