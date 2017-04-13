
package com.td.bbwp.users.testdata;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import org.witchcraft.base.test.BaseFixtureFactory;

import com.td.bbwp.users.AppUser;

//create test data for AppUser
public class AppUserFixture extends BaseFixtureFactory<AppUser> implements TemplateLoader {

	public AppUserFixture() {
		load();
	}

	@Override
	protected String getName() {
		return "appUser";
	}

	@Override
	public void load() {

		Fixture.of(AppUser.class).addTemplate(getName(), new Rule() {
			{
				add("userName", getUniqueNames());
				add("password", getUniqueNames());
				add("enabled", random(true, false));

			}
		});
	}

	protected Class<AppUser> getMyClass() {
		return AppUser.class;
	}

	public static void main(String[] args) {
		new AppUserFixture().getRecords(5).forEach(System.out::println);
	}
}
