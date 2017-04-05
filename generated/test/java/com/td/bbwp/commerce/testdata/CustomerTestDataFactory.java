
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

			customer.setDob(dateFormat.parse("2017.04.26 01:05:30 EDT"));

			customer.getAddress().setStreet("street-1491423494091-One");

			customer.getAddress().setCity("city-1491423494091-One");

			customer.getAddress().setProvince("province-1491423494091-One");

			customer.setFirstName("firstName-1491423494091-One");

			customer.setLastName("lastName-1491423494091-One");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return customer;
	}

	public static com.td.bbwp.commerce.Customer createCustomerTwo() {
		com.td.bbwp.commerce.Customer customer = new com.td.bbwp.commerce.Customer();

		try {

			register(customer);

			customer.setDob(dateFormat.parse("2017.04.29 21:43:15 EDT"));

			customer.getAddress().setStreet("street-1491423494091-Two");

			customer.getAddress().setCity("city-1491423494092-Two");

			customer.getAddress().setProvince("province-1491423494092-Two");

			customer.setFirstName("firstName-1491423494092-Two");

			customer.setLastName("lastName-1491423494092-Two");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return customer;
	}

	public static com.td.bbwp.commerce.Customer createCustomerThree() {
		com.td.bbwp.commerce.Customer customer = new com.td.bbwp.commerce.Customer();

		try {

			register(customer);

			customer.setDob(dateFormat.parse("2017.04.29 13:33:48 EDT"));

			customer.getAddress().setStreet("street-1491423494092-Three");

			customer.getAddress().setCity("city-1491423494092-Three");

			customer.getAddress().setProvince("province-1491423494093-Three");

			customer.setFirstName("firstName-1491423494093-Three");

			customer.setLastName("lastName-1491423494093-Three");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return customer;
	}

	public static com.td.bbwp.commerce.Customer createCustomerFour() {
		com.td.bbwp.commerce.Customer customer = new com.td.bbwp.commerce.Customer();

		try {

			register(customer);

			customer.setDob(dateFormat.parse("2017.04.04 14:34:21 EDT"));

			customer.getAddress().setStreet("street-1491423494093-Four");

			customer.getAddress().setCity("city-1491423494093-Four");

			customer.getAddress().setProvince("province-1491423494093-Four");

			customer.setFirstName("firstName-1491423494093-Four");

			customer.setLastName("lastName-1491423494093-Four");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return customer;
	}

	public static com.td.bbwp.commerce.Customer createCustomerFive() {
		com.td.bbwp.commerce.Customer customer = new com.td.bbwp.commerce.Customer();

		try {

			register(customer);

			customer.setDob(dateFormat.parse("2017.03.30 23:28:14 EDT"));

			customer.getAddress().setStreet("street-1491423494094-Five");

			customer.getAddress().setCity("city-1491423494094-Five");

			customer.getAddress().setProvince("province-1491423494094-Five");

			customer.setFirstName("firstName-1491423494098-Five");

			customer.setLastName("lastName-1491423494098-Five");

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
