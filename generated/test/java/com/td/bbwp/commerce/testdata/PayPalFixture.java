
package com.td.bbwp.commerce.testdata;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import org.witchcraft.base.test.BaseFixtureFactory;

import com.td.bbwp.commerce.PayPal;

//create test data for PayPal
public class PayPalFixture extends BaseFixtureFactory<PayPal> implements TemplateLoader {

	public PayPalFixture() {
		load();
	}

	@Override
	protected String getName() {
		return "payPal";
	}

	@Override
	public void load() {

		Fixture.of(PayPal.class).addTemplate(getName(), new Rule() {
			{
				add("accountAddress", getUniqueNames());
				add("paypalAccountNumber", getUniqueNames());

			}
		});
	}

	protected Class<PayPal> getMyClass() {
		return PayPal.class;
	}

	public static void main(String[] args) {
		new PayPalFixture().getRecords(5).forEach(System.out::println);
	}
}
