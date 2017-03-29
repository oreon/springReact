
package com.td.bbwp.wf.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.Date;

import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.base.dto.BaseDto;
import java.math.BigDecimal;

public class CaseDefinitionDto

		extends
			BaseDto

{

	protected String name;

	private List<TaskDefinitionDto> taskDefinitionsDto = new ArrayList<TaskDefinitionDto>();

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setTaskDefinitions(List<TaskDefinitionDto> taskDefinitionsDto) {
		this.taskDefinitionsDto = taskDefinitionsDto;
	}
	public List<TaskDefinitionDto> getTaskDefinitions() {
		return taskDefinitionsDto;
	}

	public void addTaskDefinition(TaskDefinitionDto taskDefinitionDto) {

		taskDefinitionDto.setCaseDefinition((CaseDefinitionDto) this);

		if (this.taskDefinitionsDto == null) {
			this.taskDefinitionsDto = new ArrayList<TaskDefinitionDto>();
		}

		this.taskDefinitionsDto.add(taskDefinitionDto);
	}

}
