
package com.td.bbwp.service.commerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.witchcraft.base.entity.BaseRepository;
import org.witchcraft.base.spring.BaseServiceImpl;

import com.td.bbwp.commerce.PaymentMethod;
import com.td.bbwp.web.action.commerce.PaymentMethodRepository;

@Service
@Transactional
public class PaymentMethodServiceImpl extends BaseServiceImpl<PaymentMethod> implements PaymentMethodService {

	@Autowired
	private final PaymentMethodRepository paymentMethodRepository = null;

	@Override
	public BaseRepository<PaymentMethod> getRepository() {
		return paymentMethodRepository;
	}
}
