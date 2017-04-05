
package com.td.bbwp.wf.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.Date;

import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.base.dto.BaseDto;
import java.math.BigDecimal;

public class TaskInstanceDto

		extends
			BaseDto

{

	protected Long taskId;

	protected String name;

	protected TaskDefinitionDto taskDefinitionDto;

	protected CaseInstanceDto caseInstanceDto;

	protected String taskData;

	protected status string;

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setTaskDefinition(TaskDefinitionDto taskDefinitionDto) {
		this.taskDefinitionDto = taskDefinitionDto;
	}

	public TaskDefinitionDto getTaskDefinition() {
		return taskDefinitionDto;
	}

	public void setCaseInstance(CaseInstanceDto caseInstanceDto) {
		this.caseInstanceDto = caseInstanceDto;
	}

	public CaseInstanceDto getCaseInstance() {
		return caseInstanceDto;
	}

	public void setTaskData(String taskData) {
		this.taskData = taskData;
	}

	public String getTaskData() {
		return taskData;
	}

	public void setString(status string) {
		this.string = string;
	}

	public status getString() {
		return string;
	}

}
