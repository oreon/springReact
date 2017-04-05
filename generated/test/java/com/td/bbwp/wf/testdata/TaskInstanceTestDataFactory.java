
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

public class TaskInstanceTestDataFactory extends AbstractTestDataFactory<com.td.bbwp.wf.TaskInstance> {

	private static List<com.td.bbwp.wf.TaskInstance> records = new ArrayList<com.td.bbwp.wf.TaskInstance>();

	private static final Logger logger = Logger.getLogger(TaskInstanceTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 3;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");

	static com.td.bbwp.wf.testdata.TaskDefinitionTestDataFactory taskDefinitionTestDataFactory; //= new com.td.bbwp.wf.testdata.TaskDefinitionTestDataFactory();

	static com.td.bbwp.wf.testdata.CaseInstanceTestDataFactory caseInstanceTestDataFactory; //= new com.td.bbwp.wf.testdata.CaseInstanceTestDataFactory();

	public static void register(com.td.bbwp.wf.TaskInstance taskInstance) {
		records.add(taskInstance);
	}

	public static com.td.bbwp.wf.TaskInstance createTaskInstanceOne() {
		com.td.bbwp.wf.TaskInstance taskInstance = new com.td.bbwp.wf.TaskInstance();

		try {

			register(taskInstance);

			taskInstance.setTaskId(null/*unknown attrib type:long*/);

			taskInstance.setName("name-1491423494228-One");

			taskInstance.setTaskData("taskData-1491423494228-One");

			taskInstance.setString(null/*unknown attrib type:status*/);

			taskInstance.setTaskDefinition(taskDefinitionTestDataFactory.createTaskDefinitionOne());

			taskInstance.setCaseInstance(caseInstanceTestDataFactory.createCaseInstanceOne());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return taskInstance;
	}

	public static com.td.bbwp.wf.TaskInstance createTaskInstanceTwo() {
		com.td.bbwp.wf.TaskInstance taskInstance = new com.td.bbwp.wf.TaskInstance();

		try {

			register(taskInstance);

			taskInstance.setTaskId(null/*unknown attrib type:long*/);

			taskInstance.setName("name-1491423494229-Two");

			taskInstance.setTaskData("taskData-1491423494229-Two");

			taskInstance.setString(null/*unknown attrib type:status*/);

			taskInstance.setTaskDefinition(taskDefinitionTestDataFactory.createTaskDefinitionTwo());

			taskInstance.setCaseInstance(caseInstanceTestDataFactory.createCaseInstanceTwo());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return taskInstance;
	}

	public static com.td.bbwp.wf.TaskInstance createTaskInstanceThree() {
		com.td.bbwp.wf.TaskInstance taskInstance = new com.td.bbwp.wf.TaskInstance();

		try {

			register(taskInstance);

			taskInstance.setTaskId(null/*unknown attrib type:long*/);

			taskInstance.setName("name-1491423494230-Three");

			taskInstance.setTaskData("taskData-1491423494230-Three");

			taskInstance.setString(null/*unknown attrib type:status*/);

			taskInstance.setTaskDefinition(taskDefinitionTestDataFactory.createTaskDefinitionThree());

			taskInstance.setCaseInstance(caseInstanceTestDataFactory.createCaseInstanceThree());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return taskInstance;
	}

	public static com.td.bbwp.wf.TaskInstance createTaskInstanceFour() {
		com.td.bbwp.wf.TaskInstance taskInstance = new com.td.bbwp.wf.TaskInstance();

		try {

			register(taskInstance);

			taskInstance.setTaskId(null/*unknown attrib type:long*/);

			taskInstance.setName("name-1491423494231-Four");

			taskInstance.setTaskData("taskData-1491423494231-Four");

			taskInstance.setString(null/*unknown attrib type:status*/);

			taskInstance.setTaskDefinition(taskDefinitionTestDataFactory.createTaskDefinitionFour());

			taskInstance.setCaseInstance(caseInstanceTestDataFactory.createCaseInstanceFour());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return taskInstance;
	}

	public static com.td.bbwp.wf.TaskInstance createTaskInstanceFive() {
		com.td.bbwp.wf.TaskInstance taskInstance = new com.td.bbwp.wf.TaskInstance();

		try {

			register(taskInstance);

			taskInstance.setTaskId(null/*unknown attrib type:long*/);

			taskInstance.setName("name-1491423494232-Five");

			taskInstance.setTaskData("taskData-1491423494232-Five");

			taskInstance.setString(null/*unknown attrib type:status*/);

			taskInstance.setTaskDefinition(taskDefinitionTestDataFactory.createTaskDefinitionFive());

			taskInstance.setCaseInstance(caseInstanceTestDataFactory.createCaseInstanceFive());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return taskInstance;
	}

	public static void createAll() {
		createTaskInstanceOne();
		createTaskInstanceTwo();
		createTaskInstanceThree();
		createTaskInstanceFour();
		createTaskInstanceFive();

	}

	//@Override
	public static List<com.td.bbwp.wf.TaskInstance> getListOfRecords() {
		if (records.isEmpty())
			createAll();
		return records;
	}

	//@Override
	public String getQuery() {
		return "Select e from com.td.bbwp.wf.TaskInstance e ";
	}

	/*
	public  void persistAll(){
		init();
		createAll();
	
		for (com.td.bbwp.wf.TaskInstance taskInstance : records) {
			persist(taskInstance);
		}
	}*/

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		//new TaskInstanceTestDataFactory().persistAll(); 
	}

	protected static void handleCreationException(Exception ex) {
		ex.printStackTrace();
		//in case of failure remove the last element
		//List<T> records = getListOfRecords();
		records.remove(records.size() - 1);
	}

}
