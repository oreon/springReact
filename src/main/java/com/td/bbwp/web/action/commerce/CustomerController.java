
package com.td.bbwp.web.action.commerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.witchcraft.base.spring.BaseController;
import org.witchcraft.base.spring.BaseService;

import com.td.bbwp.commerce.Customer;
import com.td.bbwp.service.commerce.CustomerService;

@RestController
@RequestMapping("/rest/customers")
public class CustomerController extends BaseController<Customer> {

	@Autowired
	private CustomerService customerService;

	@Override
	public BaseService<Customer> getBaseService() {
		return customerService;
	}

}
