
package com.td.bbwp.web.action.commerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.witchcraft.base.spring.BaseService;
import org.witchcraft.base.spring.BaseController;

import com.td.bbwp.service.commerce.OrderItemService;
import com.td.bbwp.commerce.OrderItem;

@RestController
@RequestMapping("/rest/orderItems")
public class OrderItemController extends BaseController<OrderItem> {

	@Autowired
	private OrderItemService orderItemService;

	@Override
	public BaseService<OrderItem> getBaseService() {
		return orderItemService;
	}

}
