package com.td.bbwp.wf;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TaskDefinitionTest {
	
	TaskDefinition taskDefinition; // = new TaskDefinition();
	
	@Before
	public void setup(){
		taskDefinition = new TaskDefinition();
		
//		taskDefinition.addField(new Field('age', FieldType.number, true));
//		taskDefinition.addField(new Field('dob', FieldType.date, false));
	}

	@Test
	public void testGetFormString() throws Exception {
		Field first = new Field("first", FieldType.string, true);
		first.setMax(10);
		first.setMin(2);
		taskDefinition.addField(first);
		
		String expected = "{'title': 'null','type': 'object','properties': {'first': {  'title': 'first','type': 'string', 'required':true ,'maxLength': 10,'minLength': 2}}}";

		System.out.println(taskDefinition.getForm());

		assertEquals(taskDefinition.getForm(), expected.replace('\'', '"'));
		assertNotNull(taskDefinition.getForm());
	}
	
	@Test
	public void testGetFormInt() throws Exception {
		Field first = new Field("first", FieldType.number, true);
		first.setMax(10);
		first.setMin(2);
		taskDefinition.addField(first);
		
		String expected = "{'title': 'null','type': 'object','properties': {'first': {  'title': 'first','type': 'number', 'required':true ,'maximum': 10,'minimum': 2}}}";

		System.out.println(taskDefinition.getForm());

		assertEquals(taskDefinition.getForm(), expected.replace('\'', '"'));
		assertNotNull(taskDefinition.getForm());
	}
	
	@Test
	public void testGetFormIntOnlyMin() throws Exception {
		Field first = new Field("first", FieldType.number, true);
		//first.setMax(10);
		first.setMin(2);
		taskDefinition.addField(first);
		
		String expected = "{'title': 'null','type': 'object','properties': {'first': {  'title': 'first','type': 'number', 'required':true ,'minimum': 2}}}";

		System.out.println(taskDefinition.getForm());

		assertEquals(taskDefinition.getForm(), expected.replace('\'', '"'));
		assertNotNull(taskDefinition.getForm());
	}

}
