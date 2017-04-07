
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

public class PayPalTestDataFactory extends AbstractTestDataFactory<com.td.bbwp.commerce.PayPal> {

	private static List<com.td.bbwp.commerce.PayPal> records = new ArrayList<com.td.bbwp.commerce.PayPal>();

	private static final Logger logger = Logger.getLogger(PayPalTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 3;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");

	public static void register(com.td.bbwp.commerce.PayPal payPal) {
		records.add(payPal);
	}

	public static com.td.bbwp.commerce.PayPal createPayPalOne() {
		com.td.bbwp.commerce.PayPal payPal = new com.td.bbwp.commerce.PayPal();

		try {

			register(payPal);

			payPal.setAccountAddress("accountAddress-1491586206623-One");

			payPal.setPaypalAccountNumber("paypalAccountNumber-1491586206624-One");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return payPal;
	}

	public static com.td.bbwp.commerce.PayPal createPayPalTwo() {
		com.td.bbwp.commerce.PayPal payPal = new com.td.bbwp.commerce.PayPal();

		try {

			register(payPal);

			payPal.setAccountAddress("accountAddress-1491586206624-Two");

			payPal.setPaypalAccountNumber("paypalAccountNumber-1491586206624-Two");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return payPal;
	}

	public static com.td.bbwp.commerce.PayPal createPayPalThree() {
		com.td.bbwp.commerce.PayPal payPal = new com.td.bbwp.commerce.PayPal();

		try {

			register(payPal);

			payPal.setAccountAddress("accountAddress-1491586206625-Three");

			payPal.setPaypalAccountNumber("paypalAccountNumber-1491586206625-Three");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return payPal;
	}

	public static com.td.bbwp.commerce.PayPal createPayPalFour() {
		com.td.bbwp.commerce.PayPal payPal = new com.td.bbwp.commerce.PayPal();

		try {

			register(payPal);

			payPal.setAccountAddress("accountAddress-1491586206625-Four");

			payPal.setPaypalAccountNumber("paypalAccountNumber-1491586206625-Four");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return payPal;
	}

	public static com.td.bbwp.commerce.PayPal createPayPalFive() {
		com.td.bbwp.commerce.PayPal payPal = new com.td.bbwp.commerce.PayPal();

		try {

			register(payPal);

			payPal.setAccountAddress("accountAddress-1491586206626-Five");

			payPal.setPaypalAccountNumber("paypalAccountNumber-1491586206626-Five");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return payPal;
	}

	public static void createAll() {
		createPayPalOne();
		createPayPalTwo();
		createPayPalThree();
		createPayPalFour();
		createPayPalFive();

	}

	//@Override
	public static List<com.td.bbwp.commerce.PayPal> getListOfRecords() {
		if (records.isEmpty())
			createAll();
		return records;
	}

	//@Override
	public String getQuery() {
		return "Select e from com.td.bbwp.commerce.PayPal e ";
	}

	/*
	public  void persistAll(){
		init();
		createAll();
	
		for (com.td.bbwp.commerce.PayPal payPal : records) {
			persist(payPal);
		}
	}*/

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		//new PayPalTestDataFactory().persistAll(); 
	}

	protected static void handleCreationException(Exception ex) {
		ex.printStackTrace();
		//in case of failure remove the last element
		//List<T> records = getListOfRecords();
		records.remove(records.size() - 1);
	}

}
