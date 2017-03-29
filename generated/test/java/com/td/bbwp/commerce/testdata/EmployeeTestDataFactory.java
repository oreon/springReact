
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

public class EmployeeTestDataFactory extends AbstractTestDataFactory<com.td.bbwp.commerce.Employee> {

	private static List<com.td.bbwp.commerce.Employee> records = new ArrayList<com.td.bbwp.commerce.Employee>();

	private static final Logger logger = Logger.getLogger(EmployeeTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 3;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");

	static com.td.bbwp.commerce.testdata.DepartmentTestDataFactory departmentTestDataFactory; //= new com.td.bbwp.commerce.testdata.DepartmentTestDataFactory();

	public static void register(com.td.bbwp.commerce.Employee employee) {
		records.add(employee);
	}

	public static com.td.bbwp.commerce.Employee createEmployeeOne() {
		com.td.bbwp.commerce.Employee employee = new com.td.bbwp.commerce.Employee();

		try {

			register(employee);

			employee.setDob(dateFormat.parse("2017.03.12 05:48:44 EDT"));

			employee.getAddress().setStreet("street-1490386454993-One");

			employee.getAddress().setCity("city-1490386454993-One");

			employee.getAddress().setProvince("province-1490386454993-One");

			employee.setFirstName("firstName-1490386454993-One");

			employee.setLastName("lastName-1490386454993-One");

			employee.setCode("code-1490386454993-One");

			employee.setDepartment(departmentTestDataFactory.createDepartmentOne());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return employee;
	}

	public static com.td.bbwp.commerce.Employee createEmployeeTwo() {
		com.td.bbwp.commerce.Employee employee = new com.td.bbwp.commerce.Employee();

		try {

			register(employee);

			employee.setDob(dateFormat.parse("2017.03.12 04:02:36 EDT"));

			employee.getAddress().setStreet("street-1490386454995-Two");

			employee.getAddress().setCity("city-1490386454995-Two");

			employee.getAddress().setProvince("province-1490386454996-Two");

			employee.setFirstName("firstName-1490386454996-Two");

			employee.setLastName("lastName-1490386454996-Two");

			employee.setCode("code-1490386454996-Two");

			employee.setDepartment(departmentTestDataFactory.createDepartmentTwo());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return employee;
	}

	public static com.td.bbwp.commerce.Employee createEmployeeThree() {
		com.td.bbwp.commerce.Employee employee = new com.td.bbwp.commerce.Employee();

		try {

			register(employee);

			employee.setDob(dateFormat.parse("2017.03.15 13:19:49 EDT"));

			employee.getAddress().setStreet("street-1490386454997-Three");

			employee.getAddress().setCity("city-1490386454997-Three");

			employee.getAddress().setProvince("province-1490386454997-Three");

			employee.setFirstName("firstName-1490386454998-Three");

			employee.setLastName("lastName-1490386454998-Three");

			employee.setCode("code-1490386454998-Three");

			employee.setDepartment(departmentTestDataFactory.createDepartmentThree());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return employee;
	}

	public static com.td.bbwp.commerce.Employee createEmployeeFour() {
		com.td.bbwp.commerce.Employee employee = new com.td.bbwp.commerce.Employee();

		try {

			register(employee);

			employee.setDob(dateFormat.parse("2017.04.09 03:52:04 EDT"));

			employee.getAddress().setStreet("street-1490386454999-Four");

			employee.getAddress().setCity("city-1490386454999-Four");

			employee.getAddress().setProvince("province-1490386454999-Four");

			employee.setFirstName("firstName-1490386454999-Four");

			employee.setLastName("lastName-1490386454999-Four");

			employee.setCode("code-1490386454999-Four");

			employee.setDepartment(departmentTestDataFactory.createDepartmentFour());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return employee;
	}

	public static com.td.bbwp.commerce.Employee createEmployeeFive() {
		com.td.bbwp.commerce.Employee employee = new com.td.bbwp.commerce.Employee();

		try {

			register(employee);

			employee.setDob(dateFormat.parse("2017.03.06 14:33:42 EST"));

			employee.getAddress().setStreet("street-1490386455001-Five");

			employee.getAddress().setCity("city-1490386455001-Five");

			employee.getAddress().setProvince("province-1490386455001-Five");

			employee.setFirstName("firstName-1490386455001-Five");

			employee.setLastName("lastName-1490386455001-Five");

			employee.setCode("code-1490386455001-Five");

			employee.setDepartment(departmentTestDataFactory.createDepartmentFive());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return employee;
	}

	public static void createAll() {
		createEmployeeOne();
		createEmployeeTwo();
		createEmployeeThree();
		createEmployeeFour();
		createEmployeeFive();

	}

	//@Override
	public static List<com.td.bbwp.commerce.Employee> getListOfRecords() {
		if (records.isEmpty())
			createAll();
		return records;
	}

	//@Override
	public String getQuery() {
		return "Select e from com.td.bbwp.commerce.Employee e ";
	}

	/*
	public  void persistAll(){
		init();
		createAll();
	
		for (com.td.bbwp.commerce.Employee employee : records) {
			persist(employee);
		}
	}*/

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		//new EmployeeTestDataFactory().persistAll(); 
	}

	protected static void handleCreationException(Exception ex) {
		ex.printStackTrace();
		//in case of failure remove the last element
		//List<T> records = getListOfRecords();
		records.remove(records.size() - 1);
	}

}
