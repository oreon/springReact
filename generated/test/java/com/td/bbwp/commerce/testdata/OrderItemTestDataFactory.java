
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

public class OrderItemTestDataFactory extends AbstractTestDataFactory<com.td.bbwp.commerce.OrderItem> {

	private static List<com.td.bbwp.commerce.OrderItem> records = new ArrayList<com.td.bbwp.commerce.OrderItem>();

	private static final Logger logger = Logger.getLogger(OrderItemTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 3;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");

	static com.td.bbwp.commerce.testdata.ProductTestDataFactory productTestDataFactory; //= new com.td.bbwp.commerce.testdata.ProductTestDataFactory();

	static com.td.bbwp.commerce.testdata.CustomerOrderTestDataFactory customerOrderTestDataFactory; //= new com.td.bbwp.commerce.testdata.CustomerOrderTestDataFactory();

	public static void register(com.td.bbwp.commerce.OrderItem orderItem) {
		records.add(orderItem);
	}

	public static com.td.bbwp.commerce.OrderItem createOrderItemOne() {
		com.td.bbwp.commerce.OrderItem orderItem = new com.td.bbwp.commerce.OrderItem();

		try {

			register(orderItem);

			orderItem.setQty(3270);

			orderItem.setProduct(productTestDataFactory.createProductOne());

			orderItem.setCustomerOrder(customerOrderTestDataFactory.createCustomerOrderOne());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return orderItem;
	}

	public static com.td.bbwp.commerce.OrderItem createOrderItemTwo() {
		com.td.bbwp.commerce.OrderItem orderItem = new com.td.bbwp.commerce.OrderItem();

		try {

			register(orderItem);

			orderItem.setQty(73420);

			orderItem.setProduct(productTestDataFactory.createProductTwo());

			orderItem.setCustomerOrder(customerOrderTestDataFactory.createCustomerOrderTwo());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return orderItem;
	}

	public static com.td.bbwp.commerce.OrderItem createOrderItemThree() {
		com.td.bbwp.commerce.OrderItem orderItem = new com.td.bbwp.commerce.OrderItem();

		try {

			register(orderItem);

			orderItem.setQty(52350);

			orderItem.setProduct(productTestDataFactory.createProductThree());

			orderItem.setCustomerOrder(customerOrderTestDataFactory.createCustomerOrderThree());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return orderItem;
	}

	public static com.td.bbwp.commerce.OrderItem createOrderItemFour() {
		com.td.bbwp.commerce.OrderItem orderItem = new com.td.bbwp.commerce.OrderItem();

		try {

			register(orderItem);

			orderItem.setQty(63300);

			orderItem.setProduct(productTestDataFactory.createProductFour());

			orderItem.setCustomerOrder(customerOrderTestDataFactory.createCustomerOrderFour());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return orderItem;
	}

	public static com.td.bbwp.commerce.OrderItem createOrderItemFive() {
		com.td.bbwp.commerce.OrderItem orderItem = new com.td.bbwp.commerce.OrderItem();

		try {

			register(orderItem);

			orderItem.setQty(66134);

			orderItem.setProduct(productTestDataFactory.createProductFive());

			orderItem.setCustomerOrder(customerOrderTestDataFactory.createCustomerOrderFive());

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return orderItem;
	}

	public static void createAll() {
		createOrderItemOne();
		createOrderItemTwo();
		createOrderItemThree();
		createOrderItemFour();
		createOrderItemFive();

	}

	//@Override
	public static List<com.td.bbwp.commerce.OrderItem> getListOfRecords() {
		if (records.isEmpty())
			createAll();
		return records;
	}

	//@Override
	public String getQuery() {
		return "Select e from com.td.bbwp.commerce.OrderItem e ";
	}

	/*
	public  void persistAll(){
		init();
		createAll();
	
		for (com.td.bbwp.commerce.OrderItem orderItem : records) {
			persist(orderItem);
		}
	}*/

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		//new OrderItemTestDataFactory().persistAll(); 
	}

	protected static void handleCreationException(Exception ex) {
		ex.printStackTrace();
		//in case of failure remove the last element
		//List<T> records = getListOfRecords();
		records.remove(records.size() - 1);
	}

}
