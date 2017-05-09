package com.exist.employee;

import java.util.List;

public class ModifyRoleScreenImpl implements Screen {

	private List<RoleDto> roleList;

	public ModifyRoleScreenImpl setRoleList(List<RoleDto> roleList) {
		this.roleList = roleList;
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
			 		 .addColumn(r.getRole())
					 .addColumn(new Button().setType("submit")
	                                  .setName("btn_outputRole_Employees")
	                                  .setValue(r.getRoleId() + "")
	                                  .setOutput("Show Employees")
	                                  .getStringOutput())
					 .addColumn(new Button().setType("submit")
	                                  .setName("btn_editRole")
	                                  .setValue(r.getRoleId() + "")
	                                  .setOutput("EDIT")
	                                  .getStringOutput())
					 .addColumn(new Button().setType("submit")
	                                  .setName("btn_deleteRole")
	                                  .setValue(r.getRoleId() + "")
	                                  .setOutput("DELETE")
	                                  .getStringOutput());
			 }

	      return html.setTitle("Index").addBody(table.getStringOutput()).getStringOutput();
	}

}