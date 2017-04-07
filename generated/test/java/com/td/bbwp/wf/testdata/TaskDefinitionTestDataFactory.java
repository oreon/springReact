
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

public class TaskDefinitionTestDataFactory extends AbstractTestDataFactory<com.td.bbwp.wf.TaskDefinition> {

	private static List<com.td.bbwp.wf.TaskDefinition> records = new ArrayList<com.td.bbwp.wf.TaskDefinition>();

	private static final Logger logger = Logger.getLogger(TaskDefinitionTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 3;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");

	static com.td.bbwp.wf.testdata.CaseDefinitionTestDataFactory caseDefinitionTestDataFactory; //= new com.td.bbwp.wf.testdata.CaseDefinitionTestDataFactory();

	public static void register(com.td.bbwp.wf.TaskDefinition taskDefinition) {
		records.add(taskDefinition);
	}

	public static com.td.bbwp.wf.TaskDefinition createTaskDefinitionOne() {
		com.td.bbwp.wf.TaskDefinition taskDefinition = new com.td.bbwp.wf.TaskDefinition();

		try {

			register(taskDefinition);

			taskDefinition.setName("name-1491586206672-One");

			taskDefinition.setFormSchema("formSchema-1491586206672-One");

			taskDefinition.setCaseDefinition(caseDefinitionTestDataFactory.createCaseDefinitionOne());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return taskDefinition;
	}

	public static com.td.bbwp.wf.TaskDefinition createTaskDefinitionTwo() {
		com.td.bbwp.wf.TaskDefinition taskDefinition = new com.td.bbwp.wf.TaskDefinition();

		try {

			register(taskDefinition);

			taskDefinition.setName("name-1491586206673-Two");

			taskDefinition.setFormSchema("formSchema-1491586206673-Two");

			taskDefinition.setCaseDefinition(caseDefinitionTestDataFactory.createCaseDefinitionTwo());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return taskDefinition;
	}

	public static com.td.bbwp.wf.TaskDefinition createTaskDefinitionThree() {
		com.td.bbwp.wf.TaskDefinition taskDefinition = new com.td.bbwp.wf.TaskDefinition();

		try {

			register(taskDefinition);

			taskDefinition.setName("name-1491586206674-Three");

			taskDefinition.setFormSchema("formSchema-1491586206674-Three");

			taskDefinition.setCaseDefinition(caseDefinitionTestDataFactory.createCaseDefinitionThree());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return taskDefinition;
	}

	public static com.td.bbwp.wf.TaskDefinition createTaskDefinitionFour() {
		com.td.bbwp.wf.TaskDefinition taskDefinition = new com.td.bbwp.wf.TaskDefinition();

		try {

			register(taskDefinition);

			taskDefinition.setName("name-1491586206675-Four");

			taskDefinition.setFormSchema("formSchema-1491586206675-Four");

			taskDefinition.setCaseDefinition(caseDefinitionTestDataFactory.createCaseDefinitionFour());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return taskDefinition;
	}

	public static com.td.bbwp.wf.TaskDefinition createTaskDefinitionFive() {
		com.td.bbwp.wf.TaskDefinition taskDefinition = new com.td.bbwp.wf.TaskDefinition();

		try {

			register(taskDefinition);

			taskDefinition.setName("name-1491586206738-Five");

			taskDefinition.setFormSchema("formSchema-1491586206738-Five");

			taskDefinition.setCaseDefinition(caseDefinitionTestDataFactory.createCaseDefinitionFive());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return taskDefinition;
	}

	public static void createAll() {
		createTaskDefinitionOne();
		createTaskDefinitionTwo();
		createTaskDefinitionThree();
		createTaskDefinitionFour();
		createTaskDefinitionFive();

	}

	//@Override
	public static List<com.td.bbwp.wf.TaskDefinition> getListOfRecords() {
		if (records.isEmpty())
			createAll();
		return records;
	}

	//@Override
	public String getQuery() {
		return "Select e from com.td.bbwp.wf.TaskDefinition e ";
	}

	/*
	public  void persistAll(){
		init();
		createAll();
	
		for (com.td.bbwp.wf.TaskDefinition taskDefinition : records) {
			persist(taskDefinition);
		}
	}*/

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		//new TaskDefinitionTestDataFactory().persistAll(); 
	}

	protected static void handleCreationException(Exception ex) {
		ex.printStackTrace();
		//in case of failure remove the last element
		//List<T> records = getListOfRecords();
		records.remove(records.size() - 1);
	}

}
