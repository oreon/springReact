
package com.td.bbwp.testdata;

import java.util.List;
import java.util.ArrayList;
import org.witchcraft.base.test.AbstractTestDataFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class TestDataFactoryManager {

	public static List<AbstractTestDataFactory> listDataFactory = new ArrayList<AbstractTestDataFactory>();

	//@Bean
	public com.td.bbwp.commerce.testdata.EmployeeTestDataFactory createEmployeeTestDataFactory() {
		return new com.td.bbwp.commerce.testdata.EmployeeTestDataFactory();
	}

	//@Bean
	public com.td.bbwp.commerce.testdata.DepartmentTestDataFactory createDepartmentTestDataFactory() {
		return new com.td.bbwp.commerce.testdata.DepartmentTestDataFactory();
	}

	//@Bean
	public com.td.bbwp.commerce.testdata.CustomerTestDataFactory createCustomerTestDataFactory() {
		return new com.td.bbwp.commerce.testdata.CustomerTestDataFactory();
	}

	//@Bean
	public com.td.bbwp.commerce.testdata.CustomerOrderTestDataFactory createCustomerOrderTestDataFactory() {
		return new com.td.bbwp.commerce.testdata.CustomerOrderTestDataFactory();
	}

	//@Bean
	public com.td.bbwp.commerce.testdata.ProductTestDataFactory createProductTestDataFactory() {
		return new com.td.bbwp.commerce.testdata.ProductTestDataFactory();
	}

	//@Bean
	public com.td.bbwp.commerce.testdata.OrderItemTestDataFactory createOrderItemTestDataFactory() {
		return new com.td.bbwp.commerce.testdata.OrderItemTestDataFactory();
	}

	//@Bean
	public com.td.bbwp.commerce.testdata.PaymentMethodTestDataFactory createPaymentMethodTestDataFactory() {
		return new com.td.bbwp.commerce.testdata.PaymentMethodTestDataFactory();
	}

	//@Bean
	public com.td.bbwp.commerce.testdata.CreditCardTestDataFactory createCreditCardTestDataFactory() {
		return new com.td.bbwp.commerce.testdata.CreditCardTestDataFactory();
	}

	//@Bean
	public com.td.bbwp.commerce.testdata.PayPalTestDataFactory createPayPalTestDataFactory() {
		return new com.td.bbwp.commerce.testdata.PayPalTestDataFactory();
	}

	//@Bean
	public com.td.bbwp.users.testdata.AppUserTestDataFactory createAppUserTestDataFactory() {
		return new com.td.bbwp.users.testdata.AppUserTestDataFactory();
	}

	//@Bean
	public com.td.bbwp.users.testdata.AppRoleTestDataFactory createAppRoleTestDataFactory() {
		return new com.td.bbwp.users.testdata.AppRoleTestDataFactory();
	}

	//@Bean
	public com.td.bbwp.wf.testdata.CaseDefinitionTestDataFactory createCaseDefinitionTestDataFactory() {
		return new com.td.bbwp.wf.testdata.CaseDefinitionTestDataFactory();
	}

	//@Bean
	public com.td.bbwp.wf.testdata.TaskDefinitionTestDataFactory createTaskDefinitionTestDataFactory() {
		return new com.td.bbwp.wf.testdata.TaskDefinitionTestDataFactory();
	}

	//@Bean
	public com.td.bbwp.wf.testdata.FieldTestDataFactory createFieldTestDataFactory() {
		return new com.td.bbwp.wf.testdata.FieldTestDataFactory();
	}

	static {

		listDataFactory.add(new com.td.bbwp.commerce.testdata.EmployeeTestDataFactory());

		listDataFactory.add(new com.td.bbwp.commerce.testdata.DepartmentTestDataFactory());

		listDataFactory.add(new com.td.bbwp.commerce.testdata.CustomerTestDataFactory());

		listDataFactory.add(new com.td.bbwp.commerce.testdata.CustomerOrderTestDataFactory());

		listDataFactory.add(new com.td.bbwp.commerce.testdata.ProductTestDataFactory());

		listDataFactory.add(new com.td.bbwp.commerce.testdata.OrderItemTestDataFactory());

		listDataFactory.add(new com.td.bbwp.commerce.testdata.PaymentMethodTestDataFactory());

		listDataFactory.add(new com.td.bbwp.commerce.testdata.CreditCardTestDataFactory());

		listDataFactory.add(new com.td.bbwp.commerce.testdata.PayPalTestDataFactory());

		listDataFactory.add(new com.td.bbwp.users.testdata.AppUserTestDataFactory());

		listDataFactory.add(new com.td.bbwp.users.testdata.AppRoleTestDataFactory());

		listDataFactory.add(new com.td.bbwp.wf.testdata.CaseDefinitionTestDataFactory());

		listDataFactory.add(new com.td.bbwp.wf.testdata.TaskDefinitionTestDataFactory());

		listDataFactory.add(new com.td.bbwp.wf.testdata.FieldTestDataFactory());

	}

	public void persist() {
		for (AbstractTestDataFactory dataFactory : listDataFactory) {
			dataFactory.persistAll();
		}
	}

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		//new TestDataGenerator().persist();
	}

}
