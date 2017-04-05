
package com.td.bbwp.web.action.commerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.witchcraft.base.spring.BaseService;
import org.witchcraft.base.spring.BaseController;

import com.td.bbwp.service.commerce.CreditCardService;
import com.td.bbwp.commerce.CreditCard;

@RestController
@RequestMapping("/rest/creditCards")
public class CreditCardController extends BaseController<CreditCard> {

	@Autowired
	private CreditCardService creditCardService;

	@Override
	public BaseService<CreditCard> getBaseService() {
		return creditCardService;
	}

}
