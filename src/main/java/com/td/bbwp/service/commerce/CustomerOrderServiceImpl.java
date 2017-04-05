
package com.td.bbwp.service.commerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.witchcraft.base.entity.BaseRepository;
import org.witchcraft.base.spring.BaseServiceImpl;

import com.td.bbwp.commerce.CustomerOrder;

import com.td.bbwp.web.action.commerce.CustomerOrderRepository;

@Service
@Transactional
public class CustomerOrderServiceImpl extends BaseServiceImpl<CustomerOrder> implements CustomerOrderService {

	@Autowired
	private final CustomerOrderRepository customerOrderRepository = null;

	@Override
	public BaseRepository<CustomerOrder> getRepository() {
		return customerOrderRepository;
	}
}
