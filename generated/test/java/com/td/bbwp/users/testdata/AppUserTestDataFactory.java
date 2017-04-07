
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

public class AppUserTestDataFactory extends AbstractTestDataFactory<com.td.bbwp.users.AppUser> {

	private static List<com.td.bbwp.users.AppUser> records = new ArrayList<com.td.bbwp.users.AppUser>();

	private static final Logger logger = Logger.getLogger(AppUserTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 3;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");

	public static void register(com.td.bbwp.users.AppUser appUser) {
		records.add(appUser);
	}

	public static com.td.bbwp.users.AppUser createAppUserOne() {
		com.td.bbwp.users.AppUser appUser = new com.td.bbwp.users.AppUser();

		try {

			register(appUser);

			appUser.setUserName("userName-1491586206634-One");

			appUser.setPassword("password-1491586206635-One");

			appUser.setEnabled(true);

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return appUser;
	}

	public static com.td.bbwp.users.AppUser createAppUserTwo() {
		com.td.bbwp.users.AppUser appUser = new com.td.bbwp.users.AppUser();

		try {

			register(appUser);

			appUser.setUserName("userName-1491586206635-Two");

			appUser.setPassword("password-1491586206635-Two");

			appUser.setEnabled(true);

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return appUser;
	}

	public static com.td.bbwp.users.AppUser createAppUserThree() {
		com.td.bbwp.users.AppUser appUser = new com.td.bbwp.users.AppUser();

		try {

			register(appUser);

			appUser.setUserName("userName-1491586206636-Three");

			appUser.setPassword("password-1491586206636-Three");

			appUser.setEnabled(true);

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return appUser;
	}

	public static com.td.bbwp.users.AppUser createAppUserFour() {
		com.td.bbwp.users.AppUser appUser = new com.td.bbwp.users.AppUser();

		try {

			register(appUser);

			appUser.setUserName("userName-1491586206636-Four");

			appUser.setPassword("password-1491586206637-Four");

			appUser.setEnabled(true);

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return appUser;
	}

	public static com.td.bbwp.users.AppUser createAppUserFive() {
		com.td.bbwp.users.AppUser appUser = new com.td.bbwp.users.AppUser();

		try {

			register(appUser);

			appUser.setUserName("userName-1491586206637-Five");

			appUser.setPassword("password-1491586206637-Five");

			appUser.setEnabled(true);

		} catch (Exception ex) {
			handleCreationException(ex);
		}

		return appUser;
	}

	public static void createAll() {
		createAppUserOne();
		createAppUserTwo();
		createAppUserThree();
		createAppUserFour();
		createAppUserFive();

	}

	//@Override
	public static List<com.td.bbwp.users.AppUser> getListOfRecords() {
		if (records.isEmpty())
			createAll();
		return records;
	}

	//@Override
	public String getQuery() {
		return "Select e from com.td.bbwp.users.AppUser e ";
	}

	/*
	public  void persistAll(){
		init();
		createAll();
	
		for (com.td.bbwp.users.AppUser appUser : records) {
			persist(appUser);
		}
	}*/

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		//new AppUserTestDataFactory().persistAll(); 
	}

	protected static void handleCreationException(Exception ex) {
		ex.printStackTrace();
		//in case of failure remove the last element
		//List<T> records = getListOfRecords();
		records.remove(records.size() - 1);
	}

}
