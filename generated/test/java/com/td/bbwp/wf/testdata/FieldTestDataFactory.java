
package com.td.bbwp.wf.testdata;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.base.test.AbstractTestDataFactory;

import org.springframework.beans.factory.annotation.Autowired;

//import org.witchcraft.model.support.errorhandling.BusinessException;
//import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.apache.log4j.Logger;

public class FieldTestDataFactory extends AbstractTestDataFactory<com.td.bbwp.wf.Field> {

	private static List<com.td.bbwp.wf.Field> records = new ArrayList<com.td.bbwp.wf.Field>();

	private static final Logger logger = Logger.getLogger(FieldTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 3;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");

	static com.td.bbwp.wf.testdata.TaskDefinitionTestDataFactory taskDefinitionTestDataFactory; //= new com.td.bbwp.wf.testdata.TaskDefinitionTestDataFactory();

	public static void register(com.td.bbwp.wf.Field field) {
		records.add(field);
	}

	public static com.td.bbwp.wf.Field createFieldOne() {
		com.td.bbwp.wf.Field field = new com.td.bbwp.wf.Field();

		try {

			register(field);

			field.setName("name-1490386455209-One");

			field.setType("type-1490386455209-One");

			field.setTaskDefinition(taskDefinitionTestDataFactory.createTaskDefinitionOne());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return field;
	}

	public static com.td.bbwp.wf.Field createFieldTwo() {
		com.td.bbwp.wf.Field field = new com.td.bbwp.wf.Field();

		try {

			register(field);

			field.setName("name-1490386455210-Two");

			field.setType("type-1490386455210-Two");

			field.setTaskDefinition(taskDefinitionTestDataFactory.createTaskDefinitionTwo());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return field;
	}

	public static com.td.bbwp.wf.Field createFieldThree() {
		com.td.bbwp.wf.Field field = new com.td.bbwp.wf.Field();

		try {

			register(field);

			field.setName("name-1490386455210-Three");

			field.setType("type-1490386455210-Three");

			field.setTaskDefinition(taskDefinitionTestDataFactory.createTaskDefinitionThree());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return field;
	}

	public static com.td.bbwp.wf.Field createFieldFour() {
		com.td.bbwp.wf.Field field = new com.td.bbwp.wf.Field();

		try {

			register(field);

			field.setName("name-1490386455211-Four");

			field.setType("type-1490386455211-Four");

			field.setTaskDefinition(taskDefinitionTestDataFactory.createTaskDefinitionFour());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return field;
	}

	public static com.td.bbwp.wf.Field createFieldFive() {
		com.td.bbwp.wf.Field field = new com.td.bbwp.wf.Field();

		try {

			register(field);

			field.setName("name-1490386455212-Five");

			field.setType("type-1490386455212-Five");

			field.setTaskDefinition(taskDefinitionTestDataFactory.createTaskDefinitionFive());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return field;
	}

	public static void createAll() {
		createFieldOne();
		createFieldTwo();
		createFieldThree();
		createFieldFour();
		createFieldFive();

	}

	//@Override
	public static List<com.td.bbwp.wf.Field> getListOfRecords() {
		if (records.isEmpty())
			createAll();
		return records;
	}

	//@Override
	public String getQuery() {
		return "Select e from com.td.bbwp.wf.Field e ";
	}

	/*
	public  void persistAll(){
		init();
		createAll();
	
		for (com.td.bbwp.wf.Field field : records) {
			persist(field);
		}
	}*/

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		//new FieldTestDataFactory().persistAll(); 
	}

	protected static void handleCreationException(Exception ex) {
		ex.printStackTrace();
		//in case of failure remove the last element
		//List<T> records = getListOfRecords();
		records.remove(records.size() - 1);
	}

}
