package com.exist.employee;

import java.util.List;

public class EmployeeRegisterScreenImpl implements Screen {

	private List<RoleDto> roles;
	private EmployeeDto employee;

	public EmployeeRegisterScreenImpl setRoleList(List<RoleDto> roles) {
		this.roles = roles;
		return this;
	}

	public EmployeeRegisterScreenImpl setEmployee(EmployeeDto employee) {
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
		         .addColumn("FULL NAME")
		         .addColumn(new InputType().setType("text")
		                                   .setName("txt_Title")
		                               	   .setPlaceHolder("Title")
		                                   .getStringOutput())
		         .addColumn(new InputType().setType("text")
		                                   .setName("txt_LastName")
		                               	   .setPlaceHolder("lastname")
		                               	   .setRequired("required")
		                                   .getStringOutput())
		         .addColumn(new InputType().setType("text")
		                                   .setName("txt_FirstName")
		                               	   .setPlaceHolder("Firstname")
		                               	   .setRequired("required")
		                                   .getStringOutput())
		         .addColumn(new InputType().setType("text")
		                                   .setName("txt_MiddleName")
		                               	   .setPlaceHolder("MiddleName")
		                               	   .setRequired("required")
		                                   .getStringOutput())
		         .addColumn(new InputType().setType("text")
		                                   .setName("txt_Suffix")
		                               	   .setPlaceHolder("Suffix")
		                                   .getStringOutput())

	         .addRow()
		         .addColumn("ADDRESS")
		         .addColumn(new InputType().setType("text")
		                                   .setName("txt_StreetNo")
		                               	   .setPlaceHolder("Street No.")
		                               	   .setRequired("required")
		                                   .getStringOutput())
		         .addColumn(new InputType().setType("text")
		                                   .setName("txt_Street")
		                               	   .setPlaceHolder("Street name")
		                               	   .setRequired("required")
		                                   .getStringOutput())
		         .addColumn(new InputType().setType("text")
		                                   .setName("txt_Brgy")
		                               	   .setPlaceHolder("Barangay")
		                               	   .setRequired("required")
		                                   .getStringOutput())
		         .addColumn(new InputType().setType("text")
		                                   .setName("txt_City")
		                               	   .setPlaceHolder("City")
		                               	   .setRequired("required")
		                                   .getStringOutput())
		         .addColumn(new InputType().setType("text")
		                                   .setName("txt_Zipcode")
		                               	   .setPlaceHolder("Zipcode")
		                               	   .setRequired("required")
		                                   .getStringOutput())
	         .addRow()
		         .addColumn("BIRTHDAY")
		         .addColumn(new InputType().setType("text")
		                                   .setName("txt_Birthday")
		                               	   .setPlaceHolder("YYYY-MM-DD")
		                               	   .setRequired("required")
		                                   .getStringOutput())
		     .addRow()
		     	 .addColumn("GWA")
		     	 .addColumn(new InputType().setType("text")
		                                   .setName("txt_Gwa")
		                               	   .setPlaceHolder("GWA")
		                               	   .setRequired("required")
		                                   .getStringOutput())
		     .addRow()
		         .addColumn("Date Hired")
		         .addColumn(new InputType().setType("text")
		                                   .setName("txt_DateHired")
		                               	   .setPlaceHolder("YYYY-MM-DD")
		                               	   .setRequired("required")
		                                   .getStringOutput());
		table.addRow()
				.addColumn("Contact");
				if(employee != null) {
					for(ContactDto contact : employee.getContacts()) {
						table.addColumn(contact.getContactType())
							 .addColumn(contact.getContactInfo())
						.addRow();
					}
				}
					DropDownMenu dropDownMenu = new DropDownMenu("employee_ContactType");
					for(int i = 1; i < ContactType.SIZE + 1; i++) {
						dropDownMenu.addOption(new Option(ContactType.valueOf(i).getMessage() + "",
											ContactType.valueOf(i).getMessage()));
					}
			table.addColumn(dropDownMenu.getStringOutput())
				 .addColumn(new InputType().setType("text")
		                                   .setName("employee_ContactInfo")
		                               	   .setPlaceHolder("Contact Info")
		                               	   .setRequired("required")
		                                   .getStringOutput())
				 .addColumn(new Button().setType("submit")
		                                  .setName("btn_RegEmpContact")
		                                  .setOutput("ADD")
		                                  .getStringOutput());

			table.addRow()
				.addColumn("Role");
					dropDownMenu = new DropDownMenu("employee_Role");
					for(RoleDto role : roles) {
						dropDownMenu.addOption(new Option(role.getRoleId() + "", role.getRole()));
					}
			
			table.addColumn(dropDownMenu.getStringOutput());


		return html.setTitle("Index")
		      .addBody(table.getStringOutput())
		      .addBody(new InputType().setType("submit")
	                              .setName("btn_SaveEmployee")
	                              .setValue("Save Employee")
	                              .getStringOutput())
		      .getStringOutput();
	}
	
}