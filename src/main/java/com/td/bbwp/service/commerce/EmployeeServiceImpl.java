
package com.td.bbwp.service.commerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.witchcraft.base.entity.BaseRepository;
import org.witchcraft.base.spring.BaseServiceImpl;

import com.td.bbwp.commerce.Employee;
import com.td.bbwp.web.action.commerce.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements EmployeeService {

	@Autowired
	private final EmployeeRepository employeeRepository = null;

	@Override
	public BaseRepository<Employee> getRepository() {
		return employeeRepository;
	}
}
