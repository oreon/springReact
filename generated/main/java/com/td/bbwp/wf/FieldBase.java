
/**
 *  WARN -  DO NOT MODIFY  - This file is generated by Witchcraftmda. Change Field.java instead
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

public abstract class FieldBase extends BaseEntity {

	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "NAME", unique = false)

	protected String name

	;

	@Column(name = "TYPE", unique = false)

	protected String type

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "TASK_DEFINITION_ID", nullable = false, updatable = true, insertable = true)
	@com.fasterxml.jackson.annotation.JsonBackReference

	protected TaskDefinition taskDefinition

	;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {

		return type;
	}

	public void setTaskDefinition(TaskDefinition taskDefinition) {
		this.taskDefinition = taskDefinition;
	}

	public TaskDefinition getTaskDefinition() {

		return taskDefinition;
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
		
		return lst;
	}
	*/

}