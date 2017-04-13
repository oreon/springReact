
package com.td.bbwp.wf.testdata;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import org.witchcraft.base.test.BaseFixtureFactory;

import com.td.bbwp.wf.Field;

//create test data for Field
public class FieldFixture extends BaseFixtureFactory<Field> implements TemplateLoader {

	public FieldFixture() {
		load();
	}

	@Override
	protected String getName() {
		return "field";
	}

	@Override
	public void load() {

		Fixture.of(Field.class).addTemplate(getName(), new Rule() {
			{
				add("name", getUniqueNames());
				add("type", random(com.td.bbwp.wf.FieldType.class));
				add("required", random(true, false));
				add("min", random(Integer.class, range(1, 200)));
				add("max", random(Integer.class, range(1, 200)));
				add("regularEx", getUniqueNames());

				add("taskDefinition", new com.td.bbwp.wf.testdata.TaskDefinitionFixture().getOneRecord());

			}
		});
	}

	protected Class<Field> getMyClass() {
		return Field.class;
	}

	public static void main(String[] args) {
		new FieldFixture().getRecords(5).forEach(System.out::println);
	}
}
