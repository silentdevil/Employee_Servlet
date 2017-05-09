package com.exist.employee;

import java.util.List;

public class ModifyRoleScreenImpl implements Screen {

	private List<Object[]> roleList;

	public ModifyRoleScreenImpl(List<Object[]> roleList) {
		this.roleList = roleList;
	}

	public String show() {

		HtmlObject html = new HtmlObject();
	    Table table = new Table()
	         .setFormAction("Employee")
	         .setFormMethod("POST")
	         .setBorder(1)

	         .addRow()
	         .addColumn("");


	     
	      return html.getStringOutput();
	}

}