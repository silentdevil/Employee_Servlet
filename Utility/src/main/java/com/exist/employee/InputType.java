package com.exist.employee;

public class InputType {
	protected String type;
	protected String value = "";
	protected String name;
	protected String output;
	private String required = "";
	private String placeholder = "";

	public InputType setRequired(String required) {
		this.required = required;
		return this;
	}

	public InputType setType(String type) {
		this.type = type;
		return this;
	}

	public InputType setValue(String value) {
		this.value = value;
		return this;
	}

	public InputType setName(String name) {
		this.name = name;
		return this;
	}

	public InputType setPlaceHolder(String placeholder) {
		this.placeholder = placeholder;
		return this;
	}

	public InputType setOutput(String output) {
		this.output = output;
		return this;
	}

	public String getStringOutput() {
		return "<input type=\"" + type +"\" value=\"" 
				+ value + "\" name=\"" + name + "\" placeholder=\"" + placeholder + "\"/>\n";
	}
	
}