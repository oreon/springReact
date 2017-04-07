
package com.td.bbwp.commerce.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.Date;

import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.base.dto.BaseDto;
import java.math.BigDecimal;

public class CustomerDto

		extends
			com.td.bbwp.commerce.dto.PersonDto

{

	protected String firstName;

	protected String lastName;

	private List<com.td.bbwp.wf.dto.CaseInstanceDto> caseInstancesDto = new ArrayList<com.td.bbwp.wf.dto.CaseInstanceDto>();

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

	public void setCaseInstances(List<com.td.bbwp.wf.dto.CaseInstanceDto> caseInstancesDto) {
		this.caseInstancesDto = caseInstancesDto;
	}
	public List<com.td.bbwp.wf.dto.CaseInstanceDto> getCaseInstances() {
		return caseInstancesDto;
	}

	public void addCaseInstance(com.td.bbwp.wf.dto.CaseInstanceDto caseInstanceDto) {

		caseInstanceDto.setCustomer((CustomerDto) this);

		if (this.caseInstancesDto == null) {
			this.caseInstancesDto = new ArrayList<com.td.bbwp.wf.dto.CaseInstanceDto>();
		}

		this.caseInstancesDto.add(caseInstanceDto);
	}

}
