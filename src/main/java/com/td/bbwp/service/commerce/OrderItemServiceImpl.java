
package com.td.bbwp.service.commerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.witchcraft.base.entity.BaseRepository;
import org.witchcraft.base.spring.BaseServiceImpl;

import com.td.bbwp.commerce.OrderItem;
import com.td.bbwp.web.action.commerce.OrderItemRepository;

@Service
@Transactional
public class OrderItemServiceImpl extends BaseServiceImpl<OrderItem> implements OrderItemService {

	@Autowired
	private final OrderItemRepository orderItemRepository = null;

	@Override
	public BaseRepository<OrderItem> getRepository() {
		return orderItemRepository;
	}
}
