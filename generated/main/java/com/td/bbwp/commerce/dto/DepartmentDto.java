
package com.td.bbwp.commerce.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.Date;

import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.base.dto.BaseDto;
import java.math.BigDecimal;

public class DepartmentDto

		extends
			BaseDto

{

	private List<EmployeeDto> employeesDto = new ArrayList<EmployeeDto>();

	protected String name;

	public void setEmployees(List<EmployeeDto> employeesDto) {
		this.employeesDto = employeesDto;
	}
	public List<EmployeeDto> getEmployees() {
		return employeesDto;
	}

	public void addEmployee(EmployeeDto employeeDto) {

		employeeDto.setDepartment((DepartmentDto) this);

		if (this.employeesDto == null) {
			this.employeesDto = new ArrayList<EmployeeDto>();
		}

		this.employeesDto.add(employeeDto);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
