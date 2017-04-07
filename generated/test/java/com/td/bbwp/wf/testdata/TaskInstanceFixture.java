
package com.td.bbwp.wf.testdata;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import org.witchcraft.base.test.BaseFixtureFactory;

import com.td.bbwp.wf.TaskInstance;

//create test data for TaskInstance
public class TaskInstanceFixture extends BaseFixtureFactory<TaskInstance> implements TemplateLoader {

	public TaskInstanceFixture() {
		load();
	}

	@Override
	protected String getName() {
		return "taskInstance";
	}

	@Override
	public void load() {

		Fixture.of(TaskInstance.class).addTemplate(getName(), new Rule() {
			{
				add("taskId", random(Long.class, range(1L, 100L)));
				add("name", uniqueRandom(arrayNames));
				add("taskData", uniqueRandom(arrayNames));
				add("status", uniqueRandom(arrayNames));

				add("taskDefinition", new com.td.bbwp.wf.testdata.TaskDefinitionFixture().getOneRecord());

				add("caseInstance", new com.td.bbwp.wf.testdata.CaseInstanceFixture().getOneRecord());

			}
		});
	}

	protected Class<TaskInstance> getMyClass() {
		return TaskInstance.class;
	}

	public static void main(String[] args) {
		new TaskInstanceFixture().getRecords(5).forEach(System.out::println);
	}
}
