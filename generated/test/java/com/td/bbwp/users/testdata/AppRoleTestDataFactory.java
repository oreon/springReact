
package com.td.bbwp.users.testdata;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.base.test.AbstractTestDataFactory;

import org.springframework.beans.factory.annotation.Autowired;

//import org.witchcraft.model.support.errorhandling.BusinessException;
//import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.apache.log4j.Logger;

public class AppRoleTestDataFactory extends AbstractTestDataFactory<com.td.bbwp.users.AppRole> {

	private static List<com.td.bbwp.users.AppRole> records = new ArrayList<com.td.bbwp.users.AppRole>();

	private static final Logger logger = Logger.getLogger(AppRoleTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 3;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");

	public static void register(com.td.bbwp.users.AppRole appRole) {
		records.add(appRole);
	}

	public static com.td.bbwp.users.AppRole createAppRoleOne() {
		com.td.bbwp.users.AppRole appRole = new com.td.bbwp.users.AppRole();

		try {

			register(appRole);

			appRole.setName("name-1491423494178-One");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return appRole;
	}

	public static com.td.bbwp.users.AppRole createAppRoleTwo() {
		com.td.bbwp.users.AppRole appRole = new com.td.bbwp.users.AppRole();

		try {

			register(appRole);

			appRole.setName("name-1491423494179-Two");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return appRole;
	}

	public static com.td.bbwp.users.AppRole createAppRoleThree() {
		com.td.bbwp.users.AppRole appRole = new com.td.bbwp.users.AppRole();

		try {

			register(appRole);

			appRole.setName("name-1491423494179-Three");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return appRole;
	}

	public static com.td.bbwp.users.AppRole createAppRoleFour() {
		com.td.bbwp.users.AppRole appRole = new com.td.bbwp.users.AppRole();

		try {

			register(appRole);

			appRole.setName("name-1491423494179-Four");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return appRole;
	}

	public static com.td.bbwp.users.AppRole createAppRoleFive() {
		com.td.bbwp.users.AppRole appRole = new com.td.bbwp.users.AppRole();

		try {

			register(appRole);

			appRole.setName("name-1491423494180-Five");

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return appRole;
	}

	public static void createAll() {
		createAppRoleOne();
		createAppRoleTwo();
		createAppRoleThree();
		createAppRoleFour();
		createAppRoleFive();

	}

	//@Override
	public static List<com.td.bbwp.users.AppRole> getListOfRecords() {
		if (records.isEmpty())
			createAll();
		return records;
	}

	//@Override
	public String getQuery() {
		return "Select e from com.td.bbwp.users.AppRole e ";
	}

	/*
	public  void persistAll(){
		init();
		createAll();
	
		for (com.td.bbwp.users.AppRole appRole : records) {
			persist(appRole);
		}
	}*/

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		//new AppRoleTestDataFactory().persistAll(); 
	}

	protected static void handleCreationException(Exception ex) {
		ex.printStackTrace();
		//in case of failure remove the last element
		//List<T> records = getListOfRecords();
		records.remove(records.size() - 1);
	}

}
