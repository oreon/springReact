
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

public class PaymentMethodTestDataFactory extends AbstractTestDataFactory<com.td.bbwp.commerce.PaymentMethod> {

	private static List<com.td.bbwp.commerce.PaymentMethod> records = new ArrayList<com.td.bbwp.commerce.PaymentMethod>();

	private static final Logger logger = Logger.getLogger(PaymentMethodTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 3;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");

	public static void register(com.td.bbwp.commerce.PaymentMethod paymentMethod) {
		records.add(paymentMethod);
	}

	public static com.td.bbwp.commerce.PaymentMethod createPaymentMethodOne() {
		com.td.bbwp.commerce.PaymentMethod paymentMethod = new com.td.bbwp.commerce.PaymentMethod();

		try {

			register(paymentMethod);

			paymentMethod.setAccountAddress("accountAddress-1490386455111-One");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return paymentMethod;
	}

	public static com.td.bbwp.commerce.PaymentMethod createPaymentMethodTwo() {
		com.td.bbwp.commerce.PaymentMethod paymentMethod = new com.td.bbwp.commerce.PaymentMethod();

		try {

			register(paymentMethod);

			paymentMethod.setAccountAddress("accountAddress-1490386455111-Two");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return paymentMethod;
	}

	public static com.td.bbwp.commerce.PaymentMethod createPaymentMethodThree() {
		com.td.bbwp.commerce.PaymentMethod paymentMethod = new com.td.bbwp.commerce.PaymentMethod();

		try {

			register(paymentMethod);

			paymentMethod.setAccountAddress("accountAddress-1490386455112-Three");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return paymentMethod;
	}

	public static com.td.bbwp.commerce.PaymentMethod createPaymentMethodFour() {
		com.td.bbwp.commerce.PaymentMethod paymentMethod = new com.td.bbwp.commerce.PaymentMethod();

		try {

			register(paymentMethod);

			paymentMethod.setAccountAddress("accountAddress-1490386455112-Four");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return paymentMethod;
	}

	public static com.td.bbwp.commerce.PaymentMethod createPaymentMethodFive() {
		com.td.bbwp.commerce.PaymentMethod paymentMethod = new com.td.bbwp.commerce.PaymentMethod();

		try {

			register(paymentMethod);

			paymentMethod.setAccountAddress("accountAddress-1490386455112-Five");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return paymentMethod;
	}

	public static void createAll() {
		createPaymentMethodOne();
		createPaymentMethodTwo();
		createPaymentMethodThree();
		createPaymentMethodFour();
		createPaymentMethodFive();

	}

	//@Override
	public static List<com.td.bbwp.commerce.PaymentMethod> getListOfRecords() {
		if (records.isEmpty())
			createAll();
		return records;
	}

	//@Override
	public String getQuery() {
		return "Select e from com.td.bbwp.commerce.PaymentMethod e ";
	}

	/*
	public  void persistAll(){
		init();
		createAll();
	
		for (com.td.bbwp.commerce.PaymentMethod paymentMethod : records) {
			persist(paymentMethod);
		}
	}*/

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		//new PaymentMethodTestDataFactory().persistAll(); 
	}

	protected static void handleCreationException(Exception ex) {
		ex.printStackTrace();
		//in case of failure remove the last element
		//List<T> records = getListOfRecords();
		records.remove(records.size() - 1);
	}

}
