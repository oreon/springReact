
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

@Entity
@Table(name = "TASK_DEFINITION")

@Inheritance(strategy = InheritanceType.JOINED) //inherit

public class TaskDefinition extends TaskDefinitionBase implements java.io.Serializable {
	private static final long serialVersionUID = -1594214427L;
	
	@Transient
	public String getForm(){
		
		String result = "{title: ''Todo',type: 'object',properties: {";
		
		String fields = getFields().stream().map(x ->  
		 String.format( "%s: {  title: '%s',type: '%s',  required:true}", x.getName(), x.getName(), x.getType()))
		.collect(Collectors.joining(", "));
		
		System.out.println(fields);
		return result + fields + "}}";
	}
}
