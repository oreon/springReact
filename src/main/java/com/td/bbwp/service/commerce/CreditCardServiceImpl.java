
package com.td.bbwp.service.commerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.witchcraft.base.entity.BaseRepository;
import org.witchcraft.base.spring.BaseServiceImpl;

import com.td.bbwp.commerce.CreditCard;

import com.td.bbwp.web.action.commerce.CreditCardRepository;

@Service
@Transactional
public class CreditCardServiceImpl extends BaseServiceImpl<CreditCard> implements CreditCardService {

	@Autowired
	private final CreditCardRepository creditCardRepository = null;

	@Override
	public BaseRepository<CreditCard> getRepository() {
		return creditCardRepository;
	}
}
