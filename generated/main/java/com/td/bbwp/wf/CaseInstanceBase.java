
/**
 *  WARN -  DO NOT MODIFY  - This file is generated by Witchcraftmda. Change CaseInstance.java instead
 *  Any changes will be overwritten by the next run of the code generator.
 */

package com.td.bbwp.wf;

import javax.persistence.*;
import org.witchcraft.base.entity.FileAttachment;
import org.witchcraft.base.entity.BaseEntity;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.IndexColumn;

import javax.validation.constraints.*;

import java.math.BigDecimal;

import java.util.Optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.witchcraft.base.entity.BaseEntity;

@MappedSuperclass

//@Indexed
//@Analyzer(definition = "entityAnalyzer")

public abstract class CaseInstanceBase extends BaseEntity {

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "CASE_DEFINITION_ID", nullable = false, updatable = true, insertable = true)

	protected CaseDefinition caseDefinition

	;

	@OneToMany(mappedBy = "caseInstance", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("id DESC")

	protected List<TaskInstance> taskInstances

			= new ArrayList<TaskInstance>()

	;

	//@Unique(entityName = "com.td.bbwp.wf.CaseInstance", fieldName = "PROCESS_INSTANCE_ID")

	@Column(name = "PROCESS_INSTANCE_ID", unique = true)

	protected Long processInstanceId

	;

	@Column(name = "NAME", unique = false)

	protected String name

	;

	@Column(name = "STATUS", unique = false)

	protected String status

	;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "CUSTOMER_ID", nullable = true, updatable = true, insertable = true)
	@com.fasterxml.jackson.annotation.JsonBackReference

	protected com.td.bbwp.commerce.Customer customer

	;

	public void setCaseDefinition(CaseDefinition caseDefinition) {
		this.caseDefinition = caseDefinition;
	}

	public CaseDefinition getCaseDefinition() {

		return caseDefinition;
	}

	public void setTaskInstances(List<TaskInstance> taskInstances) {
		this.taskInstances = taskInstances;
	}

	public List<TaskInstance> getTaskInstances() {

		return taskInstances;
	}

	public TaskInstance addTaskInstance(TaskInstance taskInstance) {

		taskInstance.setCaseInstance((CaseInstance) this);

		if (this.taskInstances == null) {
			this.taskInstances = new ArrayList<com.td.bbwp.wf.TaskInstance>();
		}

		this.taskInstances.add(taskInstance);

		return taskInstance;
	}

	public void addTaskInstances(List<TaskInstance> taskInstancesToAdd) {
		taskInstancesToAdd.forEach(record -> addTaskInstance(record));
	}

	@Transient
	public String createListTaskInstancesAsString() {
		return listAsString(taskInstances);
	}

	public void setProcessInstanceId(Long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public Long getProcessInstanceId() {

		return processInstanceId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {

		return status;
	}

	public void setCustomer(com.td.bbwp.commerce.Customer customer) {
		this.customer = customer;
	}

	public com.td.bbwp.commerce.Customer getCustomer() {

		return customer;
	}

	@Transient
	//Display name
	public String getDisplayName() {
		try {
			return name;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	/*
	public List<List<? extends BaseEntity>> getComposites(){
		List lst = new ArrayList();
		lst.addAll(taskInstances);
		
		return lst;
	}
	*/

}
