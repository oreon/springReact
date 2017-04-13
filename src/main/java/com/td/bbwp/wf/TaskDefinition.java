
/**
 * This file is created by Witchcraftmda only once .
 * It is OK to make changes as they will not be overwritten by subseuent re runs of the generator.
 */

package com.td.bbwp.wf;

import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

@Entity
@Table(name = "TASK_DEFINITION")

@Inheritance(strategy = InheritanceType.JOINED) //inherit

public class TaskDefinition extends TaskDefinitionBase implements java.io.Serializable {
	private static final long serialVersionUID = -1594214427L;
	
	public TaskDefinition(){}
	
	public TaskDefinition(String name) {
		this.name = name;
	}

	@Transient
	public String getForm(){
		
		String result = "{'title': '" + this.getName() + "','type': 'object','properties': {";
		
		String fields = getFields().stream().map(x ->  
		 String.format( "'%s': {  'title': '%s','type': '%s', 'required':%s %s}", x.getName(), x.getName(), getType(x),
				 (x.getRequired() ?"true":"false"),
				 getValidation(x)
				 ))
		.collect(Collectors.joining(", "));
		
		result =  (result + fields + "}}").replace("'", "\"");
				
		return result;
	}

	protected String getValidation(Field x) {
		String minInd = "minimum";
		String maxInd = "maximum";
		StringBuilder ret =  new StringBuilder();
		if(x.getType() == FieldType.string || x.getType() == FieldType.textBlob){
			minInd = "minLength";
			maxInd = "maxLength";
		}
		if(x.getMax() != null){
			ret.append(String.format("'%s': %d,", maxInd, x.getMax()) );
		}
		if(x.getMin() != null){
			ret.append(String.format("'%s': %d", minInd, x.getMin()) );
		}
		String result = ret.toString();
		return StringUtils.isEmpty(result)?result:',' + result ;
	}

	private Object getType(Field x) {
		if(x.getType().equals(FieldType.bool))
			return "boolean";
		return x.getType().toString();
	}
	
	//@PreP
}
