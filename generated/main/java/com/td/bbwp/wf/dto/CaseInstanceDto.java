
package com.td.bbwp.wf.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.Date;

import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.base.dto.BaseDto;
import java.math.BigDecimal;

public class CaseInstanceDto

		extends
			BaseDto

{

	protected CaseDefinitionDto caseDefinitionDto;

	private List<TaskInstanceDto> taskInstancesDto = new ArrayList<TaskInstanceDto>();

	protected Long processInstanceId;

	public void setCaseDefinition(CaseDefinitionDto caseDefinitionDto) {
		this.caseDefinitionDto = caseDefinitionDto;
	}

	public CaseDefinitionDto getCaseDefinition() {
		return caseDefinitionDto;
	}

	public void setTaskInstances(List<TaskInstanceDto> taskInstancesDto) {
		this.taskInstancesDto = taskInstancesDto;
	}
	public List<TaskInstanceDto> getTaskInstances() {
		return taskInstancesDto;
	}

	public void addTaskInstance(TaskInstanceDto taskInstanceDto) {

		taskInstanceDto.setCaseInstance((CaseInstanceDto) this);

		if (this.taskInstancesDto == null) {
			this.taskInstancesDto = new ArrayList<TaskInstanceDto>();
		}

		this.taskInstancesDto.add(taskInstanceDto);
	}

	public void setProcessInstanceId(Long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public Long getProcessInstanceId() {
		return processInstanceId;
	}

}
