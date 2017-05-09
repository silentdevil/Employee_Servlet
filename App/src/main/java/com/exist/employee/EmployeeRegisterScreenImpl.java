package com.exist.employee;

import java.util.List;

public class EmployeeRegisterScreenImpl implements Screen {

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
		         .addColumn("Date Hired")
		         .addColumn(new InputType().setType("text")
		                                   .setName("datehired")
		                               	   .setPlaceHolder("YYYY-MM-DD")
		                               	   .setRequired("required")
		                                   .getStringOutput());

		return html.setTitle("Index")
		      .addBody(table.getStringOutput())
		      .getStringOutput();

	}
	
}