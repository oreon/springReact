
package com.td.bbwp.users.testdata;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import org.witchcraft.base.test.BaseFixtureFactory;

import com.td.bbwp.users.AppRole;

//create test data for AppRole
public class AppRoleFixture extends BaseFixtureFactory<AppRole> implements TemplateLoader {

	public AppRoleFixture() {
		load();
	}

	@Override
	protected String getName() {
		return "appRole";
	}

	@Override
	public void load() {

		Fixture.of(AppRole.class).addTemplate(getName(), new Rule() {
			{
				add("name", getUniqueNames());

			}
		});
	}

	protected Class<AppRole> getMyClass() {
		return AppRole.class;
	}

	public static void main(String[] args) {
		new AppRoleFixture().getRecords(5).forEach(System.out::println);
	}
}
