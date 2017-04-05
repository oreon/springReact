
package com.td.bbwp.web.action.commerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.witchcraft.base.spring.BaseService;
import org.witchcraft.base.spring.BaseController;

import com.td.bbwp.service.commerce.CustomerOrderService;
import com.td.bbwp.commerce.CustomerOrder;

@RestController
@RequestMapping("/rest/customerOrders")
public class CustomerOrderController extends BaseController<CustomerOrder> {

	@Autowired
	private CustomerOrderService customerOrderService;

	@Override
	public BaseService<CustomerOrder> getBaseService() {
		return customerOrderService;
	}

}
