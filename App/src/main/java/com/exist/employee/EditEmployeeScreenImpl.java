package com.exist.employee;

import java.util.*;

public class EditEmployeeScreenImpl implements Screen {

	private EmployeeDto employee;

	public EditEmployeeScreenImpl setEmployee(EmployeeDto employee) {
		this.employee = employee;
		return this;
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
			         				.addColumn(contact.getContactInfo())
			         				.addColumn(new Button().setType("submit")
				                                  .setName("btn_DeleteEmployeeContact")
				                                  .setValue(contact.getContactId() + "")
				                                  .setOutput("DELETE")
				                                  .getStringOutput());
	         		}
	    table.addColumn(contactTable.getStringOutput())
		     .addRow()
		         .addColumn("Role");

		         	Table roleTable = new Table()
		         		.setBorder(1)
		         		.addRow();
		         		for(RoleDto role : employee.getRoles()) {
				         	   roleTable.addColumn(role.getRole())
				         				.addColumn(new Button().setType("submit")
					                                  .setName("btn_DeleteEmployeeRole")
					                                  .setValue(role.getRoleId() + "")
					                                  .setOutput("DELETE")
					                                  .getStringOutput());
		         		}
		    table.addColumn(roleTable.getStringOutput());
	    
	    

	   	return html.setTitle("Edit Employee")
	   		       .addBody(table.getStringOutput())
	   		       .addBody(new InputType().setType("submit")
	                              .setName("btn_UpdateEmployee")
	                              .setValue("Update Employee")
	                              .getStringOutput())
	   		 	   .addBody(new InputType().setType("submit")
	                              .setName("btn_Back")
	                              .setValue("Back")
	                              .getStringOutput())
	   		 	   .getStringOutput();

	}
}