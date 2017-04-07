
package com.td.bbwp.wf.testdata;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import org.witchcraft.base.test.BaseFixtureFactory;

import com.td.bbwp.wf.CaseDefinition;

//create test data for CaseDefinition
public class CaseDefinitionFixture extends BaseFixtureFactory<CaseDefinition> implements TemplateLoader {

	public CaseDefinitionFixture() {
		load();
	}

	@Override
	protected String getName() {
		return "caseDefinition";
	}

	@Override
	public void load() {

		Fixture.of(CaseDefinition.class).addTemplate(getName(), new Rule() {
			{
				add("name", uniqueRandom(arrayNames));
				add("closable", random(true, false));

			}
		});
	}

	protected Class<CaseDefinition> getMyClass() {
		return CaseDefinition.class;
	}

	public static void main(String[] args) {
		new CaseDefinitionFixture().getRecords(5).forEach(System.out::println);
	}
}
