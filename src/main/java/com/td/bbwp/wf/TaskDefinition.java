
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
import org.apache.commons.lang.WordUtils;

@Entity
@Table(name = "TASK_DEFINITION")

@Inheritance(strategy = InheritanceType.JOINED) // inherit

public class TaskDefinition extends TaskDefinitionBase implements java.io.Serializable {
	private static final long serialVersionUID = -1594214427L;

	public TaskDefinition() {
	}

	public TaskDefinition(String name) {
		this.name = name;
	}

	@Transient
	public String getForm() {

		String result = "{'title': '" + this.getName() + "','type': 'object','properties': {";

		String fields = getFields().stream()
				.map(x -> String.format("'%s': {  'title': '%s','type': '%s', 'required':%s %s}", 
						x.getName(),
						getSplitName(x.getName()), 
						getType(x),
						(x.getRequired() ? "true" : "false"), 
						getValidation(x)))
				.collect(Collectors.joining(", "));
		
		String comments = "" ; //", comments:{ type: 'string', title: 'Comments'}";

		result = (result + fields +  comments +  "}}").replace("'", "\"");

		return result;
	}

	@Transient
	public String getFormUISchema() {

		String fields = getFields().stream().filter(x -> x.getType() == FieldType.textBlob)
				.map(x -> String.format("'%s': {  'ui:widget': 'textarea'}", x.getName()))
				.collect(Collectors.joining(", "));

		return "{ comments: { 'ui:widget': 'textarea' , 'ui:placeholder': 'Comments' }," + fields + "}".replace("'", "\"");
	}

	protected String getValidation(Field x) {
		String minInd = "minimum";
		String maxInd = "maximum";
		StringBuilder ret = new StringBuilder();
		if (x.getType() == FieldType.string || x.getType() == FieldType.textBlob) {
			minInd = "minLength";
			maxInd = "maxLength";
		}
		if (x.getMax() != null) {
			ret.append(String.format("'%s': %d,", maxInd, x.getMax()));
		}
		if (x.getMin() != null) {
			ret.append(String.format("'%s': %d", minInd, x.getMin()));
		}
		String result = ret.toString();
		return StringUtils.isEmpty(result) ? result : ',' + result;
	}

	private Object getType(Field x) {
		if (x.getType().equals(FieldType.bool))
			return "boolean";
		return x.getType().toString();
	}

	private static String getSplitName(String varName) {

		varName = varName.trim();

		String result = varName.replaceAll(String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])",
				"(?<=[A-Za-z])(?=[^A-Za-z])"), " ");
		result = result.replaceAll("_", " ");

		return WordUtils.capitalizeFully(result);
	}

}
