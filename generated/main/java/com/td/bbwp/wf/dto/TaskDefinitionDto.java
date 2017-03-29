
package com.td.bbwp.wf.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.Date;

import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.base.dto.BaseDto;
import java.math.BigDecimal;

public class TaskDefinitionDto

		extends
			BaseDto

{

	protected String name;

	protected CaseDefinitionDto caseDefinitionDto;

	private List<FieldDto> fieldsDto = new ArrayList<FieldDto>();

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setCaseDefinition(CaseDefinitionDto caseDefinitionDto) {
		this.caseDefinitionDto = caseDefinitionDto;
	}

	public CaseDefinitionDto getCaseDefinition() {
		return caseDefinitionDto;
	}

	public void setFields(List<FieldDto> fieldsDto) {
		this.fieldsDto = fieldsDto;
	}
	public List<FieldDto> getFields() {
		return fieldsDto;
	}

	public void addField(FieldDto fieldDto) {

		fieldDto.setTaskDefinition((TaskDefinitionDto) this);

		if (this.fieldsDto == null) {
			this.fieldsDto = new ArrayList<FieldDto>();
		}

		this.fieldsDto.add(fieldDto);
	}

}
