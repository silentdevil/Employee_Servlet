package com.exist.employee;

import java.util.*;

public class EditEmployeeScreenImpl implements Screen {

	private EmployeeDto employee;
	private List<RoleDto> roles;

	public EditEmployeeScreenImpl setEmployee(EmployeeDto employee, List<RoleDto> roles) {
		this.employee = employee;
		this.roles = roles;
		roles.removeAll(employee.getRoles());
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

	         		contactTable.addRow()
									.addColumn("Contact");
									DropDownMenu dropDownMenu = new DropDownMenu("employee_ContactType");
									for(int i = 1; i < ContactType.SIZE + 1; i++) {
										dropDownMenu.addOption(new Option(ContactType.valueOf(i) + "",
															ContactType.valueOf(i).getMessage()));
									}
					contactTable.addColumn(dropDownMenu.getStringOutput())
								.addColumn(new InputType().setType("text")
						                                  .setName("employee_ContactInfo")
						                               	  .setPlaceHolder("Contact Info")
						                                  .setRequired("required")
						                                  .getStringOutput())
								.addColumn(new Button().setType("submit")
				                                  .setName("btn_AddEmployeeContact")
				                                  .setValue(employee.getEmployeeId() + "")
				                                  .setOutput("ADD")
				                                  .getStringOutput());


	    table.addColumn(contactTable.getStringOutput())
		     .addRow()
		         .addColumn("Role");

		         	Table roleTable = new Table()
		         		.setBorder(1)
		         		.addRow();
		         		for(RoleDto role : employee.getRoles()) {
		         			   roleTable.addRow()
				         	   			.addColumn(role.getRole())
				         				.addColumn(new Button().setType("submit")
					                                  .setName("btn_DeleteEmployeeRole")
					                                  .setValue(role.getRoleId() + "")
					                                  .setOutput("DELETE")
					                                  .getStringOutput());
		         		}
					roleTable.addRow();
							dropDownMenu = new DropDownMenu("drp_EmployeeRole");
							for(RoleDto role : roles) {
								dropDownMenu.addOption(new Option(role.getRoleId() + "", role.getRole()));
							}
			
					roleTable.addColumn(dropDownMenu.getStringOutput())
							 .addColumn(new Button().setType("submit")
					                                  .setName("btn_AddEmployeeRole")
					                                  .setValue(employee.getEmployeeId() + "")
					                                  .setOutput("Add Role")
					                                  .getStringOutput());
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