package com.exist.employee;

import java.util.List;

public class ModifyRoleScreenImpl implements Screen {

	private List<RoleDto> roleList;
	private RoleDto selectedRole;

	public ModifyRoleScreenImpl setRoleList(List<RoleDto> roleList) {
		this.roleList = roleList;
		return this;
	}

	public ModifyRoleScreenImpl setSelectedRole(RoleDto selectedRole) {
		this.selectedRole = selectedRole;
		return this;
	}

	public String show() {

		HtmlObject html = new HtmlObject();
	    Table table = new Table()
	         .setFormAction("Employee")
	         .setFormMethod("POST")
	         .setBorder(1)

	         .addRow()
		         .addColumn("ID")
		         .addColumn("Role");

			 for(RoleDto r: roleList) {
			 	table.addRow()
				 		 .addColumn(r.getRoleId() + "")
				 		 .addColumn(new InputType().setType("text")
			                                   .setName("txt_EditRole_"+r.getRoleId())
			                                   .setValue(r.getRole())
			                               	   .setPlaceHolder(r.getRole())
			                                   .getStringOutput())
						 .addColumn(new Button().setType("submit")
		                                  .setName("btn_OutputRole_Employees")
		                                  .setValue(r.getRoleId() + "")
		                                  .setOutput("Show Employees")
		                                  .getStringOutput())
						 .addColumn(new Button().setType("submit")
		                                  .setName("btn_EditRole")
		                                  .setValue(r.getRoleId() + "")
		                                  .setOutput("EDIT")
		                                  .getStringOutput())
						 .addColumn(new Button().setType("submit")
		                                  .setName("btn_DeleteRole")
		                                  .setValue(r.getRoleId() + "")
		                                  .setOutput("DELETE")
		                                  .getStringOutput());
			 }
		 table.addRow()
			  .addColumn(new InputType().setType("text")
		                                   .setName("txt_AddNewRole")
		                               	   .setPlaceHolder("New Role")
		                                   .getStringOutput())
			  .addColumn(new InputType().setType("submit")
	                              .setName("btn_AddNewRole")
	                              .setValue("Add Role")
	                              .getStringOutput());

		html.setTitle("Index").addBody(table.getStringOutput());
		
		Table employeeTable = new Table();
		if(selectedRole != null) {
			employeeTable.setFormAction("Employee")
				         .setBorder(1)
				         .addRow()
				         	.addColumn(selectedRole.getRole());
			for(Employee emp : selectedRole.getEmployees()) {
					employeeTable.addRow()
								 	.addColumn(emp.getEmployeeName().toString());
			}
		}

	    return html.addBody(employeeTable.getStringOutput())
	    		    .addBody(new InputType().setType("submit")
	                              .setName("btn_Back")
	                              .setValue("Back")
	                              .getStringOutput())
	    		    .getStringOutput();
	}

}