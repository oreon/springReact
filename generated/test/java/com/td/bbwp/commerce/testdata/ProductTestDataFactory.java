
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

public class ProductTestDataFactory extends AbstractTestDataFactory<com.td.bbwp.commerce.Product> {

	private static List<com.td.bbwp.commerce.Product> records = new ArrayList<com.td.bbwp.commerce.Product>();

	private static final Logger logger = Logger.getLogger(ProductTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 3;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");

	public static void register(com.td.bbwp.commerce.Product product) {
		records.add(product);
	}

	public static com.td.bbwp.commerce.Product createProductOne() {
		com.td.bbwp.commerce.Product product = new com.td.bbwp.commerce.Product();

		try {

			register(product);

			product.setName("name-1490822647056-One");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return product;
	}

	public static com.td.bbwp.commerce.Product createProductTwo() {
		com.td.bbwp.commerce.Product product = new com.td.bbwp.commerce.Product();

		try {

			register(product);

			product.setName("name-1490822647056-Two");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return product;
	}

	public static com.td.bbwp.commerce.Product createProductThree() {
		com.td.bbwp.commerce.Product product = new com.td.bbwp.commerce.Product();

		try {

			register(product);

			product.setName("name-1490822647057-Three");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return product;
	}

	public static com.td.bbwp.commerce.Product createProductFour() {
		com.td.bbwp.commerce.Product product = new com.td.bbwp.commerce.Product();

		try {

			register(product);

			product.setName("name-1490822647057-Four");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return product;
	}

	public static com.td.bbwp.commerce.Product createProductFive() {
		com.td.bbwp.commerce.Product product = new com.td.bbwp.commerce.Product();

		try {

			register(product);

			product.setName("name-1490822647057-Five");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return product;
	}

	public static void createAll() {
		createProductOne();
		createProductTwo();
		createProductThree();
		createProductFour();
		createProductFive();

	}

	//@Override
	public static List<com.td.bbwp.commerce.Product> getListOfRecords() {
		if (records.isEmpty())
			createAll();
		return records;
	}

	//@Override
	public String getQuery() {
		return "Select e from com.td.bbwp.commerce.Product e ";
	}

	/*
	public  void persistAll(){
		init();
		createAll();
	
		for (com.td.bbwp.commerce.Product product : records) {
			persist(product);
		}
	}*/

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		//new ProductTestDataFactory().persistAll(); 
	}

	protected static void handleCreationException(Exception ex) {
		ex.printStackTrace();
		//in case of failure remove the last element
		//List<T> records = getListOfRecords();
		records.remove(records.size() - 1);
	}

}
