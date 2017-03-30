
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

public class CaseDefinitionTestDataFactory extends AbstractTestDataFactory<com.td.bbwp.wf.CaseDefinition> {

	private static List<com.td.bbwp.wf.CaseDefinition> records = new ArrayList<com.td.bbwp.wf.CaseDefinition>();

	private static final Logger logger = Logger.getLogger(CaseDefinitionTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 3;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");

	public static void register(com.td.bbwp.wf.CaseDefinition caseDefinition) {
		records.add(caseDefinition);
	}

	public static com.td.bbwp.wf.CaseDefinition createCaseDefinitionOne() {
		com.td.bbwp.wf.CaseDefinition caseDefinition = new com.td.bbwp.wf.CaseDefinition();

		try {

			register(caseDefinition);

			caseDefinition.setName("name-1490822647131-One");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return caseDefinition;
	}

	public static com.td.bbwp.wf.CaseDefinition createCaseDefinitionTwo() {
		com.td.bbwp.wf.CaseDefinition caseDefinition = new com.td.bbwp.wf.CaseDefinition();

		try {

			register(caseDefinition);

			caseDefinition.setName("name-1490822647132-Two");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return caseDefinition;
	}

	public static com.td.bbwp.wf.CaseDefinition createCaseDefinitionThree() {
		com.td.bbwp.wf.CaseDefinition caseDefinition = new com.td.bbwp.wf.CaseDefinition();

		try {

			register(caseDefinition);

			caseDefinition.setName("name-1490822647132-Three");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return caseDefinition;
	}

	public static com.td.bbwp.wf.CaseDefinition createCaseDefinitionFour() {
		com.td.bbwp.wf.CaseDefinition caseDefinition = new com.td.bbwp.wf.CaseDefinition();

		try {

			register(caseDefinition);

			caseDefinition.setName("name-1490822647132-Four");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return caseDefinition;
	}

	public static com.td.bbwp.wf.CaseDefinition createCaseDefinitionFive() {
		com.td.bbwp.wf.CaseDefinition caseDefinition = new com.td.bbwp.wf.CaseDefinition();

		try {

			register(caseDefinition);

			caseDefinition.setName("name-1490822647133-Five");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return caseDefinition;
	}

	public static void createAll() {
		createCaseDefinitionOne();
		createCaseDefinitionTwo();
		createCaseDefinitionThree();
		createCaseDefinitionFour();
		createCaseDefinitionFive();

	}

	//@Override
	public static List<com.td.bbwp.wf.CaseDefinition> getListOfRecords() {
		if (records.isEmpty())
			createAll();
		return records;
	}

	//@Override
	public String getQuery() {
		return "Select e from com.td.bbwp.wf.CaseDefinition e ";
	}

	/*
	public  void persistAll(){
		init();
		createAll();
	
		for (com.td.bbwp.wf.CaseDefinition caseDefinition : records) {
			persist(caseDefinition);
		}
	}*/

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		//new CaseDefinitionTestDataFactory().persistAll(); 
	}

	protected static void handleCreationException(Exception ex) {
		ex.printStackTrace();
		//in case of failure remove the last element
		//List<T> records = getListOfRecords();
		records.remove(records.size() - 1);
	}

}
