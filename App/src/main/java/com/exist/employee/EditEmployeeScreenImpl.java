package com.exist.employee;

import java.util.List;

public class EditEmployeeScreenImpl implements Screen {

	private EmployeeDto employee;

	public EditEmployeeScreenImpl(EmployeeDto employee) {
		this.employee = employee;
	}

	public String show() {

		HtmlObject html = new HtmlObject();
	    Table table = new Table()
	         .setFormAction("Employee")
	         .setFormMethod("POST")
	         .setBorder(1)

	         .addRow()
	         .addColumn("Full name")
	         .addColumn(employee.getEmployeeName().toString())

	         .addRow()
	         .addColumn("Address")
	         .addColumn(employee.getAddress().toString())

	         .addRow()
	         .addColumn("Birthday")
	         .addColumn(employee.getBirthday().toString())

	         .addRow()
	         .addColumn("Address")
	         .addColumn(employee.getAddress().toString())

	         .addRow()
	         .addColumn("GWA")
	         .addColumn(employee.getGwa() + "")

	         .addRow()
	         .addColumn("Date hired")
	         .addColumn(employee.getDateHired().toString())

	         .addRow()
	         .addColumn("Contact");

	         Table contactTable = new Table()
	         		.setBorder(1)

	         		.addRow()
	         		.addColumn("Contact Type")
	         		.addColumn("Contact Info");

	         		for(ContactDto contact : employee.getContacts()) {
	         			contactTable.addRow()
			         				.addColumn(contact.getContactType())
			         				.addColumn(contact.getContactInfo());
	         		}
	    table.addColumn(contactTable.getStringOutput());

	   	return html.setTitle("Edit Employee")
	   		       .addBody(table.getStringOutput()).getStringOutput();

	}
}