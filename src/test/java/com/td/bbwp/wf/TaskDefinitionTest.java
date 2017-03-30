package com.td.bbwp.wf;

import org.junit.Before;
import org.junit.Test;

public class TaskDefinitionTest {
	
	TaskDefinition taskDefinition = new TaskDefinition();
	
	@Before
	public void setup(){
		taskDefinition.addField(new Field("first", FieldType.string, true));
		taskDefinition.addField(new Field("age", FieldType.number, true));
		taskDefinition.addField(new Field("dob", FieldType.date, false));
	}

	@Test
	public void testGetForm() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

}
