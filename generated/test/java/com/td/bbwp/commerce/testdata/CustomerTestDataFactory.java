
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

			customer.setDob(dateFormat.parse("2017.04.18 13:45:08 EDT"));

			customer.getAddress().setStreet("street-1491586206519-One");

			customer.getAddress().setCity("city-1491586206519-One");

			customer.getAddress().setProvince("province-1491586206519-One");

			customer.setFirstName("firstName-1491586206519-One");

			customer.setLastName("lastName-1491586206520-One");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return customer;
	}

	public static com.td.bbwp.commerce.Customer createCustomerTwo() {
		com.td.bbwp.commerce.Customer customer = new com.td.bbwp.commerce.Customer();

		try {

			register(customer);

			customer.setDob(dateFormat.parse("2017.04.06 23:11:15 EDT"));

			customer.getAddress().setStreet("street-1491586206521-Two");

			customer.getAddress().setCity("city-1491586206522-Two");

			customer.getAddress().setProvince("province-1491586206522-Two");

			customer.setFirstName("firstName-1491586206522-Two");

			customer.setLastName("lastName-1491586206522-Two");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return customer;
	}

	public static com.td.bbwp.commerce.Customer createCustomerThree() {
		com.td.bbwp.commerce.Customer customer = new com.td.bbwp.commerce.Customer();

		try {

			register(customer);

			customer.setDob(dateFormat.parse("2017.04.20 13:46:13 EDT"));

			customer.getAddress().setStreet("street-1491586206523-Three");

			customer.getAddress().setCity("city-1491586206523-Three");

			customer.getAddress().setProvince("province-1491586206523-Three");

			customer.setFirstName("firstName-1491586206524-Three");

			customer.setLastName("lastName-1491586206524-Three");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return customer;
	}

	public static com.td.bbwp.commerce.Customer createCustomerFour() {
		com.td.bbwp.commerce.Customer customer = new com.td.bbwp.commerce.Customer();

		try {

			register(customer);

			customer.setDob(dateFormat.parse("2017.03.25 19:15:41 EDT"));

			customer.getAddress().setStreet("street-1491586206525-Four");

			customer.getAddress().setCity("city-1491586206525-Four");

			customer.getAddress().setProvince("province-1491586206525-Four");

			customer.setFirstName("firstName-1491586206525-Four");

			customer.setLastName("lastName-1491586206525-Four");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return customer;
	}

	public static com.td.bbwp.commerce.Customer createCustomerFive() {
		com.td.bbwp.commerce.Customer customer = new com.td.bbwp.commerce.Customer();

		try {

			register(customer);

			customer.setDob(dateFormat.parse("2017.03.19 12:30:06 EDT"));

			customer.getAddress().setStreet("street-1491586206526-Five");

			customer.getAddress().setCity("city-1491586206526-Five");

			customer.getAddress().setProvince("province-1491586206526-Five");

			customer.setFirstName("firstName-1491586206527-Five");

			customer.setLastName("lastName-1491586206527-Five");

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
