
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

public class CustomerOrderTestDataFactory extends AbstractTestDataFactory<com.td.bbwp.commerce.CustomerOrder> {

	private static List<com.td.bbwp.commerce.CustomerOrder> records = new ArrayList<com.td.bbwp.commerce.CustomerOrder>();

	private static final Logger logger = Logger.getLogger(CustomerOrderTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 3;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");

	static com.td.bbwp.commerce.testdata.CustomerTestDataFactory customerTestDataFactory; //= new com.td.bbwp.commerce.testdata.CustomerTestDataFactory();

	static com.td.bbwp.commerce.testdata.PaymentMethodTestDataFactory paymentMethodTestDataFactory; //= new com.td.bbwp.commerce.testdata.PaymentMethodTestDataFactory();

	public static void register(com.td.bbwp.commerce.CustomerOrder customerOrder) {
		records.add(customerOrder);
	}

	public static com.td.bbwp.commerce.CustomerOrder createCustomerOrderOne() {
		com.td.bbwp.commerce.CustomerOrder customerOrder = new com.td.bbwp.commerce.CustomerOrder();

		try {

			register(customerOrder);

			customerOrder.setNotes("notes-1491586206542-One");

			customerOrder.setShipDate(dateFormat.parse("2017.04.22 07:57:55 EDT"));

			customerOrder.setCustomer(customerTestDataFactory.createCustomerOne());

			customerOrder.setPaymentMethod(paymentMethodTestDataFactory.createPaymentMethodOne());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return customerOrder;
	}

	public static com.td.bbwp.commerce.CustomerOrder createCustomerOrderTwo() {
		com.td.bbwp.commerce.CustomerOrder customerOrder = new com.td.bbwp.commerce.CustomerOrder();

		try {

			register(customerOrder);

			customerOrder.setNotes("notes-1491586206543-Two");

			customerOrder.setShipDate(dateFormat.parse("2017.03.22 04:09:33 EDT"));

			customerOrder.setCustomer(customerTestDataFactory.createCustomerTwo());

			customerOrder.setPaymentMethod(paymentMethodTestDataFactory.createPaymentMethodTwo());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return customerOrder;
	}

	public static com.td.bbwp.commerce.CustomerOrder createCustomerOrderThree() {
		com.td.bbwp.commerce.CustomerOrder customerOrder = new com.td.bbwp.commerce.CustomerOrder();

		try {

			register(customerOrder);

			customerOrder.setNotes("notes-1491586206544-Three");

			customerOrder.setShipDate(dateFormat.parse("2017.04.21 21:28:28 EDT"));

			customerOrder.setCustomer(customerTestDataFactory.createCustomerThree());

			customerOrder.setPaymentMethod(paymentMethodTestDataFactory.createPaymentMethodThree());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return customerOrder;
	}

	public static com.td.bbwp.commerce.CustomerOrder createCustomerOrderFour() {
		com.td.bbwp.commerce.CustomerOrder customerOrder = new com.td.bbwp.commerce.CustomerOrder();

		try {

			register(customerOrder);

			customerOrder.setNotes("notes-1491586206545-Four");

			customerOrder.setShipDate(dateFormat.parse("2017.05.02 03:32:21 EDT"));

			customerOrder.setCustomer(customerTestDataFactory.createCustomerFour());

			customerOrder.setPaymentMethod(paymentMethodTestDataFactory.createPaymentMethodFour());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return customerOrder;
	}

	public static com.td.bbwp.commerce.CustomerOrder createCustomerOrderFive() {
		com.td.bbwp.commerce.CustomerOrder customerOrder = new com.td.bbwp.commerce.CustomerOrder();

		try {

			register(customerOrder);

			customerOrder.setNotes("notes-1491586206547-Five");

			customerOrder.setShipDate(dateFormat.parse("2017.03.28 18:43:26 EDT"));

			customerOrder.setCustomer(customerTestDataFactory.createCustomerFive());

			customerOrder.setPaymentMethod(paymentMethodTestDataFactory.createPaymentMethodFive());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return customerOrder;
	}

	public static void createAll() {
		createCustomerOrderOne();
		createCustomerOrderTwo();
		createCustomerOrderThree();
		createCustomerOrderFour();
		createCustomerOrderFive();

	}

	//@Override
	public static List<com.td.bbwp.commerce.CustomerOrder> getListOfRecords() {
		if (records.isEmpty())
			createAll();
		return records;
	}

	//@Override
	public String getQuery() {
		return "Select e from com.td.bbwp.commerce.CustomerOrder e ";
	}

	/*
	public  void persistAll(){
		init();
		createAll();
	
		for (com.td.bbwp.commerce.CustomerOrder customerOrder : records) {
			persist(customerOrder);
		}
	}*/

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		//new CustomerOrderTestDataFactory().persistAll(); 
	}

	protected static void handleCreationException(Exception ex) {
		ex.printStackTrace();
		//in case of failure remove the last element
		//List<T> records = getListOfRecords();
		records.remove(records.size() - 1);
	}

}
