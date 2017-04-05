
package com.td.bbwp.web.action.commerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.witchcraft.base.spring.BaseService;
import org.witchcraft.base.spring.BaseController;

import com.td.bbwp.service.commerce.DepartmentService;
import com.td.bbwp.commerce.Department;

@RestController
@RequestMapping("/rest/departments")
public class DepartmentController extends BaseController<Department> {

	@Autowired
	private DepartmentService departmentService;

	@Override
	public BaseService<Department> getBaseService() {
		return departmentService;
	}

}
