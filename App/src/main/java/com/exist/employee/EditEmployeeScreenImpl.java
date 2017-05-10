package com.exist.employee;

import java.util.*;

public class EditEmployeeScreenImpl implements Screen {

	private EmployeeDto employee;
	private List<RoleDto> roles;
	private NameDto employeeName;
	private AddressDto employeeAddress;

	public EditEmployeeScreenImpl setEmployee(EmployeeDto employee, List<RoleDto> roles) {
		this.employee = employee;
		this.roles = roles;
		employeeName = employee.getEmployeeName();
		employeeAddress = employee.getAddress();
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
	         .addColumn(new InputType().setType("text")
			                                   .setName("txt_Title")
			                                   .setValue(employeeName.getTitle())
			                               	   .setPlaceHolder(employeeName.getTitle())
			                                   .getStringOutput())
	         .addColumn(new InputType().setType("text")
			                                   .setName("txt_LastName")
			                                   .setValue(employeeName.getLastName())
			                               	   .setPlaceHolder(employeeName.getLastName())
			                                   .getStringOutput())
	         .addColumn(new InputType().setType("text")
			                                   .setName("txt_FirstName")
			                                   .setValue(employeeName.getFirstName())
			                               	   .setPlaceHolder(employeeName.getFirstName())
			                                   .getStringOutput())
	         .addColumn(new InputType().setType("text")
			                                   .setName("txt_MiddleName")
			                                   .setValue(employeeName.getMiddleName())
			                               	   .setPlaceHolder(employeeName.getMiddleName())
			                                   .getStringOutput())
	         .addColumn(new InputType().setType("text")
			                                   .setName("txt_Suffix")
			                                   .setValue(employeeName.getSuffix())
			                               	   .setPlaceHolder(employeeName.getSuffix())
			                                   .getStringOutput())
						

	         .addRow()
	         .addColumn("Address")
	         .addColumn(new InputType().setType("text")
			                                   .setName("txt_StreetNo")
			                                   .setValue(employeeAddress.getStreetNo() + "")
			                               	   .setPlaceHolder(employeeAddress.getStreetNo() + "")
			                                   .getStringOutput())
	         .addColumn(new InputType().setType("text")
			                                   .setName("txt_Street")
			                                   .setValue(employeeAddress.getStreet())
			                               	   .setPlaceHolder(employeeAddress.getStreet())
			                                   .getStringOutput())
	         .addColumn(new InputType().setType("text")
			                                   .setName("txt_Brgy")
			                                   .setValue(employeeAddress.getBrgy())
			                               	   .setPlaceHolder(employeeAddress.getBrgy())
			                                   .getStringOutput())
	         .addColumn(new InputType().setType("text")
			                                   .setName("txt_City")
			                                   .setValue(employeeAddress.getCity())
			                               	   .setPlaceHolder(employeeAddress.getCity())
			                                   .getStringOutput())
	         .addColumn(new InputType().setType("text")
			                                   .setName("txt_Zipcode")
			                                   .setValue(employeeAddress.getZipcode())
			                               	   .setPlaceHolder(employeeAddress.getZipcode())
			                                   .getStringOutput())

	         .addRow()
	         .addColumn("Birthday")
	         .addColumn(new InputType().setType("text")
			                                   .setName("txt_Birthday")
			                                   .setValue(employee.getBirthday().toString())
			                               	   .setPlaceHolder(employee.getBirthday().toString())
			                                   .getStringOutput())

	         .addRow()
	         .addColumn("GWA")
	         .addColumn(new InputType().setType("text")
			                                   .setName("txt_Gwa")
			                                   .setValue(employee.getGwa() + "")
			                               	   .setPlaceHolder(employee.getGwa() + "")
			                                   .getStringOutput())

	         .addRow()
	         .addColumn("Date hired")
	         .addColumn(new InputType().setType("text")
			                                   .setName("txt_DateHired")
			                                   .setValue(employee.getDateHired().toString())
			                               	   .setPlaceHolder(employee.getDateHired().toString())
			                                   .getStringOutput());

	        html.addBody(table.getStringOutput());

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

	         		contactTable.addRow();
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


	    html.addBody(contactTable.getStringOutput());
		
		html.addBody("\n\nRole\n");
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
		   html.addBody(roleTable.getStringOutput());

	    
	    

	   	return html.setTitle("Edit Employee")
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