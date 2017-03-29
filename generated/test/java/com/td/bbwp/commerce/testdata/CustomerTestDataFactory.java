
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

public class CustomerTestDataFactory extends AbstractTestDataFactory<com.td.bbwp.commerce.Customer> {

	private static List<com.td.bbwp.commerce.Customer> records = new ArrayList<com.td.bbwp.commerce.Customer>();

	private static final Logger logger = Logger.getLogger(CustomerTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 3;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");

	public static void register(com.td.bbwp.commerce.Customer customer) {
		records.add(customer);
	}

	public static com.td.bbwp.commerce.Customer createCustomerOne() {
		com.td.bbwp.commerce.Customer customer = new com.td.bbwp.commerce.Customer();

		try {

			register(customer);

			customer.setDob(dateFormat.parse("2017.04.14 07:21:31 EDT"));

			customer.getAddress().setStreet("street-1490386455060-One");

			customer.getAddress().setCity("city-1490386455060-One");

			customer.getAddress().setProvince("province-1490386455060-One");

			customer.setFirstName("firstName-1490386455060-One");

			customer.setLastName("lastName-1490386455060-One");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return customer;
	}

	public static com.td.bbwp.commerce.Customer createCustomerTwo() {
		com.td.bbwp.commerce.Customer customer = new com.td.bbwp.commerce.Customer();

		try {

			register(customer);

			customer.setDob(dateFormat.parse("2017.03.14 17:32:04 EDT"));

			customer.getAddress().setStreet("street-1490386455061-Two");

			customer.getAddress().setCity("city-1490386455061-Two");

			customer.getAddress().setProvince("province-1490386455061-Two");

			customer.setFirstName("firstName-1490386455061-Two");

			customer.setLastName("lastName-1490386455062-Two");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return customer;
	}

	public static com.td.bbwp.commerce.Customer createCustomerThree() {
		com.td.bbwp.commerce.Customer customer = new com.td.bbwp.commerce.Customer();

		try {

			register(customer);

			customer.setDob(dateFormat.parse("2017.03.29 10:19:49 EDT"));

			customer.getAddress().setStreet("street-1490386455062-Three");

			customer.getAddress().setCity("city-1490386455062-Three");

			customer.getAddress().setProvince("province-1490386455063-Three");

			customer.setFirstName("firstName-1490386455063-Three");

			customer.setLastName("lastName-1490386455063-Three");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return customer;
	}

	public static com.td.bbwp.commerce.Customer createCustomerFour() {
		com.td.bbwp.commerce.Customer customer = new com.td.bbwp.commerce.Customer();

		try {

			register(customer);

			customer.setDob(dateFormat.parse("2017.03.03 12:57:02 EST"));

			customer.getAddress().setStreet("street-1490386455064-Four");

			customer.getAddress().setCity("city-1490386455064-Four");

			customer.getAddress().setProvince("province-1490386455064-Four");

			customer.setFirstName("firstName-1490386455064-Four");

			customer.setLastName("lastName-1490386455064-Four");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return customer;
	}

	public static com.td.bbwp.commerce.Customer createCustomerFive() {
		com.td.bbwp.commerce.Customer customer = new com.td.bbwp.commerce.Customer();

		try {

			register(customer);

			customer.setDob(dateFormat.parse("2017.02.28 22:29:49 EST"));

			customer.getAddress().setStreet("street-1490386455065-Five");

			customer.getAddress().setCity("city-1490386455065-Five");

			customer.getAddress().setProvince("province-1490386455065-Five");

			customer.setFirstName("firstName-1490386455065-Five");

			customer.setLastName("lastName-1490386455065-Five");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return customer;
	}

	public static void createAll() {
		createCustomerOne();
		createCustomerTwo();
		createCustomerThree();
		createCustomerFour();
		createCustomerFive();

	}

	//@Override
	public static List<com.td.bbwp.commerce.Customer> getListOfRecords() {
		if (records.isEmpty())
			createAll();
		return records;
	}

	//@Override
	public String getQuery() {
		return "Select e from com.td.bbwp.commerce.Customer e ";
	}

	/*
	public  void persistAll(){
		init();
		createAll();
	
		for (com.td.bbwp.commerce.Customer customer : records) {
			persist(customer);
		}
	}*/

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		//new CustomerTestDataFactory().persistAll(); 
	}

	protected static void handleCreationException(Exception ex) {
		ex.printStackTrace();
		//in case of failure remove the last element
		//List<T> records = getListOfRecords();
		records.remove(records.size() - 1);
	}

}
