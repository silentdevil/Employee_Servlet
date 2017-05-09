package com.exist.employee;

import java.util.List;

public class EmployeeRegisterScreenImpl implements Screen {

	private List<RoleDto> roles;

	public EmployeeRegisterScreenImpl setRoleList(List<RoleDto> roles) {
		this.roles = roles;
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
		                                   .setName("title")
		                               	   .setPlaceHolder("Title")
		                                   .getStringOutput())
		         .addColumn(new InputType().setType("text")
		                                   .setName("firstname")
		                               	   .setPlaceHolder("Firstname")
		                               	   .setRequired("required")
		                                   .getStringOutput())
		         .addColumn(new InputType().setType("text")
		                                   .setName("middlename")
		                               	   .setPlaceHolder("Middlename")
		                               	   .setRequired("required")
		                                   .getStringOutput())
		         .addColumn(new InputType().setType("text")
		                                   .setName("lastname")
		                               	   .setPlaceHolder("Lastname")
		                               	   .setRequired("required")
		                                   .getStringOutput())
		         .addColumn(new InputType().setType("text")
		                                   .setName("suffix")
		                               	   .setPlaceHolder("Suffix")
		                                   .getStringOutput())

	         .addRow()
		         .addColumn("ADDRESS")
		         .addColumn(new InputType().setType("text")
		                                   .setName("streetno")
		                               	   .setPlaceHolder("Street No.")
		                               	   .setRequired("required")
		                                   .getStringOutput())
		         .addColumn(new InputType().setType("text")
		                                   .setName("street")
		                               	   .setPlaceHolder("Street name")
		                               	   .setRequired("required")
		                                   .getStringOutput())
		         .addColumn(new InputType().setType("text")
		                                   .setName("brgy")
		                               	   .setPlaceHolder("Barangay")
		                               	   .setRequired("required")
		                                   .getStringOutput())
		         .addColumn(new InputType().setType("text")
		                                   .setName("city")
		                               	   .setPlaceHolder("City")
		                               	   .setRequired("required")
		                                   .getStringOutput())
		         .addColumn(new InputType().setType("text")
		                                   .setName("zipcode")
		                               	   .setPlaceHolder("Zipcode")
		                               	   .setRequired("required")
		                                   .getStringOutput())
	         .addRow()
		         .addColumn("BIRTHDAY")
		         .addColumn(new InputType().setType("text")
		                                   .setName("birthday")
		                               	   .setPlaceHolder("YYYY-MM-DD")
		                               	   .setRequired("required")
		                                   .getStringOutput())
		     .addRow()
		     	 .addColumn("GWA")
		     	 .addColumn(new InputType().setType("text")
		                                   .setName("gwa")
		                               	   .setPlaceHolder("GWA")
		                               	   .setRequired("required")
		                                   .getStringOutput())
		     .addRow()
		         .addColumn("Date Hired")
		         .addColumn(new InputType().setType("text")
		                                   .setName("datehired")
		                               	   .setPlaceHolder("YYYY-MM-DD")
		                               	   .setRequired("required")
		                                   .getStringOutput());
		table.addRow()
				.addColumn("Contact");
					DropDownMenu dropDownMenu = new DropDownMenu("employee_ContactType");
					for(int i = 1; i < ContactType.SIZE + 1; i++) {
						dropDownMenu.addOption(new Option(ContactType.valueOf(i) + "",
											ContactType.valueOf(i).getMessage()));
					}
			table.addColumn(dropDownMenu.getStringOutput())
				 .addColumn(new InputType().setType("text")
		                                   .setName("employee_ContactInfo")
		                               	   .setPlaceHolder("Contact Info")
		                               	   .setRequired("required")
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