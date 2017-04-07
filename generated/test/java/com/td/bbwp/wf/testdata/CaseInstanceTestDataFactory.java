
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

public class CaseInstanceTestDataFactory extends AbstractTestDataFactory<com.td.bbwp.wf.CaseInstance> {

	private static List<com.td.bbwp.wf.CaseInstance> records = new ArrayList<com.td.bbwp.wf.CaseInstance>();

	private static final Logger logger = Logger.getLogger(CaseInstanceTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 3;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");

	static com.td.bbwp.wf.testdata.CaseDefinitionTestDataFactory caseDefinitionTestDataFactory; //= new com.td.bbwp.wf.testdata.CaseDefinitionTestDataFactory();

	static com.td.bbwp.commerce.testdata.CustomerTestDataFactory customerTestDataFactory; //= new com.td.bbwp.commerce.testdata.CustomerTestDataFactory();

	public static void register(com.td.bbwp.wf.CaseInstance caseInstance) {
		records.add(caseInstance);
	}

	public static com.td.bbwp.wf.CaseInstance createCaseInstanceOne() {
		com.td.bbwp.wf.CaseInstance caseInstance = new com.td.bbwp.wf.CaseInstance();

		try {

			register(caseInstance);

			caseInstance.setProcessInstanceId(null/*unknown attrib type:long*/);

			caseInstance.setName("name-1491586206776-One");

			caseInstance.setStatus("status-1491586206776-One");

			caseInstance.setCaseDefinition(caseDefinitionTestDataFactory.createCaseDefinitionOne());

			caseInstance.setCustomer(customerTestDataFactory.createCustomerOne());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return caseInstance;
	}

	public static com.td.bbwp.wf.CaseInstance createCaseInstanceTwo() {
		com.td.bbwp.wf.CaseInstance caseInstance = new com.td.bbwp.wf.CaseInstance();

		try {

			register(caseInstance);

			caseInstance.setProcessInstanceId(null/*unknown attrib type:long*/);

			caseInstance.setName("name-1491586206777-Two");

			caseInstance.setStatus("status-1491586206777-Two");

			caseInstance.setCaseDefinition(caseDefinitionTestDataFactory.createCaseDefinitionTwo());

			caseInstance.setCustomer(customerTestDataFactory.createCustomerTwo());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return caseInstance;
	}

	public static com.td.bbwp.wf.CaseInstance createCaseInstanceThree() {
		com.td.bbwp.wf.CaseInstance caseInstance = new com.td.bbwp.wf.CaseInstance();

		try {

			register(caseInstance);

			caseInstance.setProcessInstanceId(null/*unknown attrib type:long*/);

			caseInstance.setName("name-1491586206777-Three");

			caseInstance.setStatus("status-1491586206777-Three");

			caseInstance.setCaseDefinition(caseDefinitionTestDataFactory.createCaseDefinitionThree());

			caseInstance.setCustomer(customerTestDataFactory.createCustomerThree());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return caseInstance;
	}

	public static com.td.bbwp.wf.CaseInstance createCaseInstanceFour() {
		com.td.bbwp.wf.CaseInstance caseInstance = new com.td.bbwp.wf.CaseInstance();

		try {

			register(caseInstance);

			caseInstance.setProcessInstanceId(null/*unknown attrib type:long*/);

			caseInstance.setName("name-1491586206778-Four");

			caseInstance.setStatus("status-1491586206778-Four");

			caseInstance.setCaseDefinition(caseDefinitionTestDataFactory.createCaseDefinitionFour());

			caseInstance.setCustomer(customerTestDataFactory.createCustomerFour());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return caseInstance;
	}

	public static com.td.bbwp.wf.CaseInstance createCaseInstanceFive() {
		com.td.bbwp.wf.CaseInstance caseInstance = new com.td.bbwp.wf.CaseInstance();

		try {

			register(caseInstance);

			caseInstance.setProcessInstanceId(null/*unknown attrib type:long*/);

			caseInstance.setName("name-1491586206779-Five");

			caseInstance.setStatus("status-1491586206779-Five");

			caseInstance.setCaseDefinition(caseDefinitionTestDataFactory.createCaseDefinitionFive());

			caseInstance.setCustomer(customerTestDataFactory.createCustomerFive());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return caseInstance;
	}

	public static void createAll() {
		createCaseInstanceOne();
		createCaseInstanceTwo();
		createCaseInstanceThree();
		createCaseInstanceFour();
		createCaseInstanceFive();

	}

	//@Override
	public static List<com.td.bbwp.wf.CaseInstance> getListOfRecords() {
		if (records.isEmpty())
			createAll();
		return records;
	}

	//@Override
	public String getQuery() {
		return "Select e from com.td.bbwp.wf.CaseInstance e ";
	}

	/*
	public  void persistAll(){
		init();
		createAll();
	
		for (com.td.bbwp.wf.CaseInstance caseInstance : records) {
			persist(caseInstance);
		}
	}*/

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		//new CaseInstanceTestDataFactory().persistAll(); 
	}

	protected static void handleCreationException(Exception ex) {
		ex.printStackTrace();
		//in case of failure remove the last element
		//List<T> records = getListOfRecords();
		records.remove(records.size() - 1);
	}

}
