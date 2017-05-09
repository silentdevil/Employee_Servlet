package com.exist.employee;

import java.util.*;

public class DropDownMenu {
	
	public List<Option> options = new ArrayList<>();
	private String name;

	public DropDownMenu(String name) {
		this.name = name;
	}


	public DropDownMenu addOption(Option option) {
		options.add(option);
		return this;
	}

	public String getStringOutput() {
		String stringOutput = "<select name=\"" + name + "\">\n";
		for(Option option : options) {
			stringOutput += option.getStringOutput();
		}
		return stringOutput + "</select>";
	}

}

class Option {
	private String value;
	private String output;

	public Option(String value, String output) {
		this.value = value;
		this.output = output;
	}

	public String getStringOutput() {
		return "<option value=\"" + value + "\">\n\t" + output +"\n</option>";
	}
}
