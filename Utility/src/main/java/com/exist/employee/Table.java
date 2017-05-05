package com.exist.employee;

import java.util.*;

public class Table {
	private List<Row> rows = new ArrayList<>();
	private String formAction;
	private String formMethod;
	private int currentRow;
	private int currentColumn;
	private int border;

	public Table setValue(int row, int col, String value) {
		//rows.get(row).getValues().get(col) = value;
		return this;
	}

	public Table setBorder(int border) {
		this.border = border;
		return this;
	}

	public Table addRow() {
		rows.add(new Row());
		currentRow++;
		return this;
	}

	public Table addColumn(String value) {
		rows.get(currentRow - 1).getValues().add(value);
		return this;
	}

	public Table setFormAction(String formAction) {
		this.formAction = formAction;
		return this;
	}

	public Table setFormMethod(String formMethod) {
		this.formMethod = formMethod;
		return this;
	}

	public String getStringOutput() {
		String stringOutput = "<form action=\"" + formAction + "\" method=\"" + formMethod + "\"><table border=\""+ border + "\">";
		for(Row row : rows) {
			stringOutput += row;
		}
		return stringOutput + "</table>";
	}
}

class Row {
	private List<String> values = new ArrayList<>();

	public void setValues(List<String> values){
		this.values = values;
	}

	public List<String> getValues() {
		return values;
	}

	public String toString() {
		String output = "<tr>";

		for(String string : values)
			output += "<td>" + string + "</td>";

		return output + "</tr>";
	}
}