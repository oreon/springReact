
package com.td.bbwp.wf.testdata;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import org.witchcraft.base.test.BaseFixtureFactory;

import com.td.bbwp.wf.CaseInstance;

//create test data for CaseInstance
public class CaseInstanceFixture extends BaseFixtureFactory<CaseInstance> implements TemplateLoader {

	public CaseInstanceFixture() {
		load();
	}

	@Override
	protected String getName() {
		return "caseInstance";
	}

	@Override
	public void load() {

		Fixture.of(CaseInstance.class).addTemplate(getName(), new Rule() {
			{
				add("processInstanceId", random(Long.class, range(1L, 100L)));
				add("name", getUniqueNames());
				add("status", random(com.td.bbwp.wf.CaseStatus.class));

				add("caseDefinition", new com.td.bbwp.wf.testdata.CaseDefinitionFixture().getOneRecord());

				add("customer", new com.td.bbwp.commerce.testdata.CustomerFixture().getOneRecord());

			}
		});
	}

	protected Class<CaseInstance> getMyClass() {
		return CaseInstance.class;
	}

	public static void main(String[] args) {
		new CaseInstanceFixture().getRecords(5).forEach(System.out::println);
	}
}
