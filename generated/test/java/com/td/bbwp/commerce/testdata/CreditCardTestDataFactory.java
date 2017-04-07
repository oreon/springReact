
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

public class CreditCardTestDataFactory extends AbstractTestDataFactory<com.td.bbwp.commerce.CreditCard> {

	private static List<com.td.bbwp.commerce.CreditCard> records = new ArrayList<com.td.bbwp.commerce.CreditCard>();

	private static final Logger logger = Logger.getLogger(CreditCardTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 3;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");

	public static void register(com.td.bbwp.commerce.CreditCard creditCard) {
		records.add(creditCard);
	}

	public static com.td.bbwp.commerce.CreditCard createCreditCardOne() {
		com.td.bbwp.commerce.CreditCard creditCard = new com.td.bbwp.commerce.CreditCard();

		try {

			register(creditCard);

			creditCard.setAccountAddress("accountAddress-1491586206610-One");

			creditCard.setCcNumber("ccNumber-1491586206610-One");

			creditCard.setExpiry(dateFormat.parse("2017.04.08 02:51:15 EDT"));

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return creditCard;
	}

	public static com.td.bbwp.commerce.CreditCard createCreditCardTwo() {
		com.td.bbwp.commerce.CreditCard creditCard = new com.td.bbwp.commerce.CreditCard();

		try {

			register(creditCard);

			creditCard.setAccountAddress("accountAddress-1491586206611-Two");

			creditCard.setCcNumber("ccNumber-1491586206612-Two");

			creditCard.setExpiry(dateFormat.parse("2017.03.23 11:40:06 EDT"));

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return creditCard;
	}

	public static com.td.bbwp.commerce.CreditCard createCreditCardThree() {
		com.td.bbwp.commerce.CreditCard creditCard = new com.td.bbwp.commerce.CreditCard();

		try {

			register(creditCard);

			creditCard.setAccountAddress("accountAddress-1491586206612-Three");

			creditCard.setCcNumber("ccNumber-1491586206612-Three");

			creditCard.setExpiry(dateFormat.parse("2017.04.14 19:12:53 EDT"));

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return creditCard;
	}

	public static com.td.bbwp.commerce.CreditCard createCreditCardFour() {
		com.td.bbwp.commerce.CreditCard creditCard = new com.td.bbwp.commerce.CreditCard();

		try {

			register(creditCard);

			creditCard.setAccountAddress("accountAddress-1491586206613-Four");

			creditCard.setCcNumber("ccNumber-1491586206613-Four");

			creditCard.setExpiry(dateFormat.parse("2017.03.13 17:43:26 EDT"));

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return creditCard;
	}

	public static com.td.bbwp.commerce.CreditCard createCreditCardFive() {
		com.td.bbwp.commerce.CreditCard creditCard = new com.td.bbwp.commerce.CreditCard();

		try {

			register(creditCard);

			creditCard.setAccountAddress("accountAddress-1491586206614-Five");

			creditCard.setCcNumber("ccNumber-1491586206614-Five");

			creditCard.setExpiry(dateFormat.parse("2017.03.15 03:19:33 EDT"));

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return creditCard;
	}

	public static void createAll() {
		createCreditCardOne();
		createCreditCardTwo();
		createCreditCardThree();
		createCreditCardFour();
		createCreditCardFive();

	}

	//@Override
	public static List<com.td.bbwp.commerce.CreditCard> getListOfRecords() {
		if (records.isEmpty())
			createAll();
		return records;
	}

	//@Override
	public String getQuery() {
		return "Select e from com.td.bbwp.commerce.CreditCard e ";
	}

	/*
	public  void persistAll(){
		init();
		createAll();
	
		for (com.td.bbwp.commerce.CreditCard creditCard : records) {
			persist(creditCard);
		}
	}*/

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		//new CreditCardTestDataFactory().persistAll(); 
	}

	protected static void handleCreationException(Exception ex) {
		ex.printStackTrace();
		//in case of failure remove the last element
		//List<T> records = getListOfRecords();
		records.remove(records.size() - 1);
	}

}
