
package com.td.bbwp.web.action.commerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.witchcraft.base.spring.BaseController;
import org.witchcraft.base.spring.BaseService;

import com.td.bbwp.commerce.Employee;
import com.td.bbwp.service.commerce.EmployeeService;

@RestController
@RequestMapping("/rest/employees")
public class EmployeeController extends BaseController<Employee> {

	@Autowired
	private EmployeeService employeeService;

	@Override
	public BaseService<Employee> getBaseService() {
		return employeeService;
	}

}
