
package com.td.bbwp.commerce.testdata;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.base.test.AbstractTestDataFactory;

import org.springframework.beans.factory.annotation.Autowired;

//import org.witchcraft.model.support.errorhandling.BusinessException;
//import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.apache.log4j.Logger;

public class DepartmentTestDataFactory extends AbstractTestDataFactory<com.td.bbwp.commerce.Department> {

	private static List<com.td.bbwp.commerce.Department> records = new ArrayList<com.td.bbwp.commerce.Department>();

	private static final Logger logger = Logger.getLogger(DepartmentTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 3;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");

	public static void register(com.td.bbwp.commerce.Department department) {
		records.add(department);
	}

	public static com.td.bbwp.commerce.Department createDepartmentOne() {
		com.td.bbwp.commerce.Department department = new com.td.bbwp.commerce.Department();

		try {

			register(department);

			department.setName("name-1490903698292-One");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return department;
	}

	public static com.td.bbwp.commerce.Department createDepartmentTwo() {
		com.td.bbwp.commerce.Department department = new com.td.bbwp.commerce.Department();

		try {

			register(department);

			department.setName("name-1490903698292-Two");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return department;
	}

	public static com.td.bbwp.commerce.Department createDepartmentThree() {
		com.td.bbwp.commerce.Department department = new com.td.bbwp.commerce.Department();

		try {

			register(department);

			department.setName("name-1490903698292-Three");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return department;
	}

	public static com.td.bbwp.commerce.Department createDepartmentFour() {
		com.td.bbwp.commerce.Department department = new com.td.bbwp.commerce.Department();

		try {

			register(department);

			department.setName("name-1490903698293-Four");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return department;
	}

	public static com.td.bbwp.commerce.Department createDepartmentFive() {
		com.td.bbwp.commerce.Department department = new com.td.bbwp.commerce.Department();

		try {

			register(department);

			department.setName("name-1490903698293-Five");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return department;
	}

	public static void createAll() {
		createDepartmentOne();
		createDepartmentTwo();
		createDepartmentThree();
		createDepartmentFour();
		createDepartmentFive();

	}

	//@Override
	public static List<com.td.bbwp.commerce.Department> getListOfRecords() {
		if (records.isEmpty())
			createAll();
		return records;
	}

	//@Override
	public String getQuery() {
		return "Select e from com.td.bbwp.commerce.Department e ";
	}

	/*
	public  void persistAll(){
		init();
		createAll();
	
		for (com.td.bbwp.commerce.Department department : records) {
			persist(department);
		}
	}*/

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		//new DepartmentTestDataFactory().persistAll(); 
	}

	protected static void handleCreationException(Exception ex) {
		ex.printStackTrace();
		//in case of failure remove the last element
		//List<T> records = getListOfRecords();
		records.remove(records.size() - 1);
	}

}
