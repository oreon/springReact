
package com.td.bbwp.commerce.testdata;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import org.witchcraft.base.test.BaseFixtureFactory;

import com.td.bbwp.commerce.CreditCard;

//create test data for CreditCard
public class CreditCardFixture extends BaseFixtureFactory<CreditCard> implements TemplateLoader {

	public CreditCardFixture() {
		load();
	}

	@Override
	protected String getName() {
		return "creditCard";
	}

	@Override
	public void load() {

		Fixture.of(CreditCard.class).addTemplate(getName(), new Rule() {
			{
				add("accountAddress", uniqueRandom(arrayNames));
				add("ccNumber", uniqueRandom(arrayNames));
				add("expiry", randomDate("2011-04-15", "2011-11-07", new SimpleDateFormat("yyyy-MM-dd")));

			}
		});
	}

	protected Class<CreditCard> getMyClass() {
		return CreditCard.class;
	}

	public static void main(String[] args) {
		new CreditCardFixture().getRecords(5).forEach(System.out::println);
	}
}
