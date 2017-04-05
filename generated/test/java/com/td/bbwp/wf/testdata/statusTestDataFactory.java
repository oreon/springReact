
package com.td.bbwp.wf.testdata;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.base.test.AbstractTestDataFactory;

import org.springframework.beans.factory.annotation.Autowired;

//import org.witchcraft.model.support.errorhandling.BusinessException;
//import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.apache.log4j.Logger;

public class statusTestDataFactory extends AbstractTestDataFactory<com.td.bbwp.wf.status> {

	private static List<com.td.bbwp.wf.status> records = new ArrayList<com.td.bbwp.wf.status>();

	private static final Logger logger = Logger.getLogger(statusTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 3;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");

	public static void register(com.td.bbwp.wf.status status) {
		records.add(status);
	}

	public static com.td.bbwp.wf.status createstatusOne() {
		com.td.bbwp.wf.status status = new com.td.bbwp.wf.status();

		try {

			register(status);

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return status;
	}

	public static com.td.bbwp.wf.status createstatusTwo() {
		com.td.bbwp.wf.status status = new com.td.bbwp.wf.status();

		try {

			register(status);

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return status;
	}

	public static com.td.bbwp.wf.status createstatusThree() {
		com.td.bbwp.wf.status status = new com.td.bbwp.wf.status();

		try {

			register(status);

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return status;
	}

	public static com.td.bbwp.wf.status createstatusFour() {
		com.td.bbwp.wf.status status = new com.td.bbwp.wf.status();

		try {

			register(status);

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return status;
	}

	public static com.td.bbwp.wf.status createstatusFive() {
		com.td.bbwp.wf.status status = new com.td.bbwp.wf.status();

		try {

			register(status);

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return status;
	}

	public static void createAll() {
		createstatusOne();
		createstatusTwo();
		createstatusThree();
		createstatusFour();
		createstatusFive();

	}

	//@Override
	public static List<com.td.bbwp.wf.status> getListOfRecords() {
		if (records.isEmpty())
			createAll();
		return records;
	}

	//@Override
	public String getQuery() {
		return "Select e from com.td.bbwp.wf.status e ";
	}

	/*
	public  void persistAll(){
		init();
		createAll();
	
		for (com.td.bbwp.wf.status status : records) {
			persist(status);
		}
	}*/

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		//new statusTestDataFactory().persistAll(); 
	}

	protected static void handleCreationException(Exception ex) {
		ex.printStackTrace();
		//in case of failure remove the last element
		//List<T> records = getListOfRecords();
		records.remove(records.size() - 1);
	}

}
