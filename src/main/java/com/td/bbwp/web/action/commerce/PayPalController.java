
package com.td.bbwp.web.action.commerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.witchcraft.base.spring.BaseService;
import org.witchcraft.base.spring.BaseController;

import com.td.bbwp.service.commerce.PayPalService;
import com.td.bbwp.commerce.PayPal;

@RestController
@RequestMapping("/rest/payPals")
public class PayPalController extends BaseController<PayPal> {

	@Autowired
	private PayPalService payPalService;

	@Override
	public BaseService<PayPal> getBaseService() {
		return payPalService;
	}

}
