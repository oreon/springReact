package com.td.bbwp.wf;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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
		String expected = "{\"title\": \"null\",\"type\": \"object\",\"properties\":" +
		"{\"first\": {  \"title\": \"first\",\"type\": \"string\", \"required\":true}, " + 
			"\"age\": {  \"title\": \"age\",\"type\": \"number\", \"required\":true}, " + 
			"\"dob\":{  \"title\": \"dob\",\"type\": \"date\", \"required\":false}}" + 
		"}";

		assertEquals(taskDefinition.getForm(), expected);
	}

}
