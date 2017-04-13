
package com.td.bbwp.commerce.testdata;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import org.witchcraft.base.test.BaseFixtureFactory;

import com.td.bbwp.commerce.Product;

//create test data for Product
public class ProductFixture extends BaseFixtureFactory<Product> implements TemplateLoader {

	public ProductFixture() {
		load();
	}

	@Override
	protected String getName() {
		return "product";
	}

	@Override
	public void load() {

		Fixture.of(Product.class).addTemplate(getName(), new Rule() {
			{
				add("name", random(getUniqueNames()));

			}
		});
	}

	protected Class<Product> getMyClass() {
		return Product.class;
	}

	public static void main(String[] args) {
		new ProductFixture().getRecords(5).forEach(System.out::println);
	}
}
