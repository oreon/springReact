
package com.td.bbwp.service.commerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.witchcraft.base.entity.BaseRepository;
import org.witchcraft.base.spring.BaseServiceImpl;

import com.td.bbwp.commerce.Customer;
import com.td.bbwp.web.action.commerce.CustomerRepository;

@Service
@Transactional
public class CustomerServiceImpl extends BaseServiceImpl<Customer> implements CustomerService {

	@Autowired
	private final CustomerRepository customerRepository = null;

	@Override
	public BaseRepository<Customer> getRepository() {
		return customerRepository;
	}
}
