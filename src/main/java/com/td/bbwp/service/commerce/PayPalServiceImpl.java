
package com.td.bbwp.service.commerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.witchcraft.base.entity.BaseRepository;
import org.witchcraft.base.spring.BaseServiceImpl;

import com.td.bbwp.commerce.PayPal;

import com.td.bbwp.web.action.commerce.PayPalRepository;

@Service
@Transactional
public class PayPalServiceImpl extends BaseServiceImpl<PayPal> implements PayPalService {

	@Autowired
	private final PayPalRepository payPalRepository = null;

	@Override
	public BaseRepository<PayPal> getRepository() {
		return payPalRepository;
	}
}
