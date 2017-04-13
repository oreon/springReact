
package com.td.bbwp.web.action.commerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.witchcraft.base.spring.BaseController;
import org.witchcraft.base.spring.BaseService;

import com.td.bbwp.commerce.PaymentMethod;
import com.td.bbwp.service.commerce.PaymentMethodService;

@RestController
@RequestMapping("/rest/paymentMethods")
public class PaymentMethodController extends BaseController<PaymentMethod> {

	@Autowired
	private PaymentMethodService paymentMethodService;

	@Override
	public BaseService<PaymentMethod> getBaseService() {
		return paymentMethodService;
	}

}
