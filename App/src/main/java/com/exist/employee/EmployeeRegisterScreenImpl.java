package com.exist.employee;

import java.util.List;

public class EmployeeRegisterScreenImpl implements Screen {

	private List<RoleDto> roles;
	private EmployeeDto employee;

	public EmployeeRegisterScreenImpl setRoleList(List<RoleDto> roles) {
		this.roles = roles;
		roles.removeAll(employee.getRoles());
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
		                                   .setValue((employee.getEmployeeName().getTitle() == null) ?
		                                   			 "" : employee.getEmployeeName().getTitle())
		                               	   .setPlaceHolder("Title")
		                                   .getStringOutput())
		         .addColumn(new InputType().setType("text")
		                                   .setName("txt_LastName")
		                                   .setValue((employee.getEmployeeName().getLastName() == null) ? 
		                                   			 "" : employee.getEmployeeName().getLastName())
		                               	   .setPlaceHolder("lastname")
		                               	   .setRequired("required")
		                                   .getStringOutput())
		         .addColumn(new InputType().setType("text")
		                                   .setName("txt_FirstName")
		                                   .setValue((employee.getEmployeeName().getFirstName() == null) ?
		                                   			"" : employee.getEmployeeName().getLastName())
		                               	   .setPlaceHolder("Firstname")
		                               	   .setRequired("required")
		                                   .getStringOutput())
		         .addColumn(new InputType().setType("text")
		                                   .setName("txt_MiddleName")
		                                   .setValue((employee.getEmployeeName().getMiddleName() == null) ? 
		                                   			 "" : employee.getEmployeeName().getMiddleName())
		                               	   .setPlaceHolder("MiddleName")
		                               	   .setRequired("required")
		                                   .getStringOutput())
		         .addColumn(new InputType().setType("text")
		         						   .setValue((employee.getEmployeeName().getSuffix() == null) ? 
		         						   			 "" : employee.getEmployeeName().getSuffix())
		                                   .setName("txt_Suffix")
		                               	   .setPlaceHolder("Suffix")
		                                   .getStringOutput())

	         .addRow()
		         .addColumn("ADDRESS")
		         .addColumn(new InputType().setType("text")
		                                   .setName("txt_StreetNo")
		                                   .setValue((employee.getAddress().getStreetNo() == null) ? 
		                                   			 "" : employee.getAddress().getStreetNo())
		                               	   .setPlaceHolder("Street No.")
		                               	   .setRequired("required")
		                                   .getStringOutput())
		         .addColumn(new InputType().setType("text")
		                                   .setName("txt_Street")
		                                   .setValue((employee.getAddress().getStreet() == null) ?
		                                    		 "" : employee.getAddress().getStreet())
		                               	   .setPlaceHolder("Street name")
		                               	   .setRequired("required")
		                                   .getStringOutput())
		         .addColumn(new InputType().setType("text")
		                                   .setName("txt_Brgy")
		                                   .setValue((employee.getAddress().getBrgy() == null) ? 
		                                    		 "" : employee.getAddress().getBrgy())
		                               	   .setPlaceHolder("Barangay")
		                               	   .setRequired("required")
		                                   .getStringOutput())
		         .addColumn(new InputType().setType("text")
		                                   .setName("txt_City")
		                                   .setValue((employee.getAddress().getCity() == null) ?
		                                    		 "" : employee.getAddress().getCity())
		                               	   .setPlaceHolder("City")
		                               	   .setRequired("required")
		                                   .getStringOutput())
		         .addColumn(new InputType().setType("text")
		                                   .setName("txt_Zipcode")
		                                   .setValue((employee.getAddress().getZipcode() == null) ?
		                                    		 "" : employee.getAddress().getZipcode())
		                               	   .setPlaceHolder("Zipcode")
		                               	   .setRequired("required")
		                                   .getStringOutput())
	         .addRow()
		         .addColumn("BIRTHDAY")
		         .addColumn(new InputType().setType("text")
		                                   .setName("txt_Birthday")
		                                   .setValue((employee.getBirthday() == null) ?
		                                    		 "" : employee.getBirthday() + "")
		                               	   .setPlaceHolder("YYYY-MM-DD")
		                               	   .setRequired("required")
		                                   .getStringOutput())
		     .addRow()
		     	 .addColumn("GWA")
		     	 .addColumn(new InputType().setType("text")
		                                   .setName("txt_Gwa")
		                                   .setValue(employee.getGwa() + "")
		                               	   .setPlaceHolder("GWA")
		                               	   .setRequired("required")
		                                   .getStringOutput())
		     .addRow()
		         .addColumn("Date Hired")
		         .addColumn(new InputType().setType("text")
		                                   .setName("txt_DateHired")
		                                   .setValue((employee.getDateHired() == null) ?
		                                   			 "" : employee.getDateHired() + "")
		                               	   .setPlaceHolder("YYYY-MM-DD")
		                               	   .setRequired("required")
		                                   .getStringOutput())
		     .addRow()
		    	 .addColumn("Currently Hired");
			    	 DropDownMenu dropDownMenu = new DropDownMenu("drp_CurrentlyHired");
			    	 dropDownMenu.addOption(new Option("TRUE","TRUE"));
			    	 dropDownMenu.addOption(new Option("FALSE","FALSE"));
		    	table.addColumn(dropDownMenu.getStringOutput());
		    	 

		table.addRow()
				.addColumn("Contact");
					if(employee.getContacts().size() > 0) {
						for(ContactDto contact : employee.getContacts()) {
							table.addColumn(contact.getContactType())
								 .addColumn(contact.getContactInfo())
								 .addColumn(new Button().setType("submit")
		                                  .setName("btn_RegEmpContactDelete")
		                                  .setOutput("DELETE")
		                                  .getStringOutput())
							.addRow()
							.addColumn("");
						}
					}
					dropDownMenu = new DropDownMenu("employee_ContactType");
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
					if(employee !=null) {
						for(RoleDto role : employee.getRoles()) {
							table.addColumn(role.getRole())
								 .addColumn(new Button().setType("submit")
		                                  .setName("btn_RegEmpRoleDelete")
		                                  .setValue(role.getRoleId() + "")
		                                  .setOutput("DELETE")
		                                  .getStringOutput())
							.addRow();

						}	
					}
					dropDownMenu = new DropDownMenu("employee_Role");
					for(RoleDto role : roles) {
						dropDownMenu.addOption(new Option(role.getRoleId() + "", role.getRole()));
					}
			
			table.addColumn(dropDownMenu.getStringOutput())
				 .addColumn(new Button().setType("submit")
		                                .setName("btn_RegEmpRole")
		                                .setOutput("ADD")
		                                .getStringOutput());


		return html.setTitle("Index")
		      .addBody(table.getStringOutput())
		      .addBody(new InputType().setType("submit")
	                              .setName("btn_SaveEmployee")
	                              .setValue("Save Employee")
	                              .getStringOutput())
		      .addBody(new InputType().setType("submit")
	                              .setName("btn_Back")
	                              .setValue("Back")
	                              .getStringOutput())
		      .getStringOutput();
	}
	
}