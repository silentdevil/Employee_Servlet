package com.exist.employee;

import java.util.List;

public class IndexScreenImpl implements Screen {

	private List<Object[]> empList;

	public IndexScreenImpl(List<Object[]> empList) {
		this.empList = empList;
	}

	public String show() {

		HtmlObject html = new HtmlObject();
	    Table table = new Table()
	         .setFormAction("Employee")
	         .setFormMethod("POST")
	         .setBorder(1)
	         .addRow()
	         .addColumn("ID").addColumn("Firstname").addColumn("Middlename");

	    for(Object[] obj: empList) {
	      table.addRow()
	           .addColumn(obj[0] + "")
	           .addColumn(obj[1] + "")
	           .addColumn(new Button().setType("submit")
	                                  .setName("edit")
	                                  .setValue(obj[0] + "")
	                                  .setOutput("EDIT")
	                                  .getStringOutput())
	           .addColumn(new Button().setType("submit")
	                                  .setName("delete")
	                                  .setValue(obj[0] + "")
	                                  .setOutput("DELETE")
	                                  .getStringOutput());
	    }
	         
	    html.setTitle("Index")
	      .addBody(table.getStringOutput())
	      .addBody(new InputType().setType("submit")
	                                   .setName("addEmp")
	                                   .setValue("Add Employee")
	                                   .getStringOutput())
	      .addBody(new InputType().setType("submit")
	                              .setName("sort_gwa")
	                              .setValue("Sort by GWA")
	                              .getStringOutput())
	      .addBody(new InputType().setType("submit")
	                              .setName("sort_date")
	                              .setValue("Sort by Hiredate")
	                              .getStringOutput())
	      .addBody(new InputType().setType("submit")
	                              .setName("sort_lastname")
	                              .setValue("Sort by Lastname")
	                              .getStringOutput())
	      .addBody(new InputType().setType("submit")
	                              .setName("modifyRoles")
	                              .setValue("Modify Roles")
	                              .getStringOutput());
	      return html.getStringOutput();
	}

}