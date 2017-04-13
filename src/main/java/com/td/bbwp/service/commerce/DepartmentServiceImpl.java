
package com.td.bbwp.service.commerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.witchcraft.base.entity.BaseRepository;
import org.witchcraft.base.spring.BaseServiceImpl;

import com.td.bbwp.commerce.Department;
import com.td.bbwp.web.action.commerce.DepartmentRepository;

@Service
@Transactional
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService {

	@Autowired
	private final DepartmentRepository departmentRepository = null;

	@Override
	public BaseRepository<Department> getRepository() {
		return departmentRepository;
	}
}
