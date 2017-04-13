
package com.td.bbwp.wf.testdata;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import org.witchcraft.base.test.BaseFixtureFactory;

import com.td.bbwp.wf.TaskDefinition;

//create test data for TaskDefinition
public class TaskDefinitionFixture extends BaseFixtureFactory<TaskDefinition> implements TemplateLoader {

	public TaskDefinitionFixture() {
		load();
	}

	@Override
	protected String getName() {
		return "taskDefinition";
	}

	@Override
	public void load() {

		Fixture.of(TaskDefinition.class).addTemplate(getName(), new Rule() {
			{
				add("name", getUniqueNames());
				add("formSchema", getUniqueNames());

				add("caseDefinition", new com.td.bbwp.wf.testdata.CaseDefinitionFixture().getOneRecord());

			}
		});
	}

	protected Class<TaskDefinition> getMyClass() {
		return TaskDefinition.class;
	}

	public static void main(String[] args) {
		new TaskDefinitionFixture().getRecords(5).forEach(System.out::println);
	}
}
