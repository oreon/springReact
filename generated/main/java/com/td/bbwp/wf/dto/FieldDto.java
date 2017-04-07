
package com.td.bbwp.wf.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.Date;

import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.base.dto.BaseDto;
import java.math.BigDecimal;

public class FieldDto

		extends
			BaseDto

{

	protected String name;

	protected FieldType type;

	protected TaskDefinitionDto taskDefinitionDto;

	protected Boolean required;

	protected Integer min;

	protected Integer max;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setType(FieldType type) {
		this.type = type;
	}

	public FieldType getType() {
		return type;
	}

	public void setTaskDefinition(TaskDefinitionDto taskDefinitionDto) {
		this.taskDefinitionDto = taskDefinitionDto;
	}

	public TaskDefinitionDto getTaskDefinition() {
		return taskDefinitionDto;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMin() {
		return min;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Integer getMax() {
		return max;
	}

}
