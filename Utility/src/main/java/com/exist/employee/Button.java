package com.exist.employee;

public class Button extends InputType {
	
	private String onClick;

	public InputType setOnclick(String onClick) {
		this.onClick = onClick;
		return this;
	}

	public String getStringOutput() {
		return "<button type=\"" + type +"\" value=\"" 
				+ value + "\" name=\"" + name + "\" onclick=\"" + onClick + "\">" + output 
				+ "</button>";
	}
}

