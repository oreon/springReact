
package com.td.bbwp.commerce.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.Date;

import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.base.dto.BaseDto;
import java.math.BigDecimal;

public class EmployeeDto

		extends
			com.td.bbwp.commerce.dto.PersonDto

{

	protected DepartmentDto departmentDto;

	protected String firstName;

	protected String lastName;

	protected String code;

	public void setDepartment(DepartmentDto departmentDto) {
		this.departmentDto = departmentDto;
	}

	public DepartmentDto getDepartment() {
		return departmentDto;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
