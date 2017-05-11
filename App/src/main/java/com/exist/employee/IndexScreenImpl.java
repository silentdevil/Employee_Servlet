package com.exist.employee;

import java.util.List;

public class IndexScreenImpl implements Screen {

	private List<Object[]> empList;

	public IndexScreenImpl setEmpList(List<Object[]> empList) {
		this.empList = empList;
		return this;
	}

	public List<Object[]> getEmpList() {
		return empList;
	}
	
	public String show() {

		HtmlObject html = new HtmlObject();
	    Table table = new Table()
	         .setFormAction("Employee")
	         .setFormMethod("POST")
	         .setBorder(1)
	         .addRow()
	         .addColumn("ID").addColumn("Fullname");

	    for(Object[] obj: empList) {
	      table.addRow()
	           .addColumn(obj[0] + "")
	           .addColumn(obj[1] + "")
	           .addColumn(obj[2] + "")
	           .addColumn(new Button().setType("submit")
	                                  .setName("btn_EditEmployee")
	                                  .setValue(obj[0] + "")
	                                  .setOutput("EDIT")
	                                  .getStringOutput())
	           .addColumn(new Button().setType("submit")
	                                  .setName("btn_DeleteEmployee")
	                                  .setValue(obj[0] + "")
	                                  .setOutput("DELETE")
	                                  .getStringOutput());
	    }
	         
	    html.setTitle("Index")
	      .addBody(table.getStringOutput())
	      .addBody(new InputType().setType("submit")
	                                   .setName("btn_AddEmp")
	                                   .setValue("Add Employee")
	                                   .getStringOutput())
	      .addBody(new InputType().setType("submit")
	                              .setName("btn_SortGwa")
	                              .setValue("Sort by GWA")
	                              .getStringOutput())
	      .addBody(new InputType().setType("submit")
	                              .setName("btn_SortDate")
	                              .setValue("Sort by Hiredate")
	                              .getStringOutput())
	      .addBody(new InputType().setType("submit")
	                              .setName("btn_SortLastname")
	                              .setValue("Sort by Lastname")
	                              .getStringOutput())
	      .addBody(new InputType().setType("submit")
	                              .setName("btn_ModifyRoles")
	                              .setValue("Modify Roles")
	                              .getStringOutput());
	      return html.getStringOutput();
	}

}