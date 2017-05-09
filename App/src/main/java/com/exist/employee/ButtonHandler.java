package com.exist.employee;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class ButtonHandler {

	private ButtonFunctions buttonFunctions;
	/*
	private Screen[] screens = {indexScreen, editEmployeeScreen, 
                employeeRegisterScreen, modifyRoleScreen};
	screen[0] = indexScreen
	screen[1] = editEmployeeScreen
	screen[2] = employeeRegisterScreen
	screen[3] = modifyRoleScreen
	*/
	private IndexScreenImpl indexScreen;
	private EditEmployeeScreenImpl editEmployeeScreen;
	private EmployeeRegisterScreenImpl employeeRegisterScreen;
	private ModifyRoleScreenImpl modifyRoleScreen;
	private List<Object[]> obj;
	public ButtonHandler(ButtonFunctions buttonFunctions, Screen[] screens) {
		this.buttonFunctions = buttonFunctions;

		indexScreen = (IndexScreenImpl) screens[0];
		editEmployeeScreen = (EditEmployeeScreenImpl) screens[1];
		employeeRegisterScreen = (EmployeeRegisterScreenImpl) screens[2];
		modifyRoleScreen = (ModifyRoleScreenImpl) screens[3];
		indexScreen.setEmpList(obj);

	}

	public Screen handle(HttpServletRequest request,
	                  HttpServletResponse response) throws ServletException, IOException {
		Screen screen = null;
		if(request.getParameter("btn_AddEmp")!=null) {
			employeeRegisterScreen.setRoleList(buttonFunctions.getAllRoles());
			screen = employeeRegisterScreen;
		} else if(request.getParameter("btn_SortGwa")!=null) {
			indexScreen.setEmpList(buttonFunctions.sort("gwa"));
			screen = indexScreen;
		} else if(request.getParameter("btn_SortDate")!=null) {
			indexScreen.setEmpList(buttonFunctions.sort("dateHired"));
			screen = indexScreen;
		} else if(request.getParameter("btn_Lastname")!=null) {
			indexScreen.setEmpList(buttonFunctions.sort("dateHired"));
			screen = indexScreen;
		} else if(request.getParameter("btn_EditEmployee") != null) {
			long id = Long.valueOf(request.getParameter("btn_EditEmployee"));
			EmployeeDto employee = buttonFunctions.editEmployee(id);
			editEmployeeScreen.setEmployee(employee);
			screen = editEmployeeScreen;
		} else if(request.getParameter("btn_ModifyRoles") != null) {
			modifyRoleScreen.setRoleList(buttonFunctions.getAllRoles());
			screen = modifyRoleScreen;
		} else if(request.getParameter("btn_DeleteEmployee") != null) {
			long id = Long.valueOf(request.getParameter("btn_DeleteEmployee"));
			buttonFunctions.deleteEmployee(id);
			indexScreen.setEmpList(buttonFunctions.sort("dateHired"));
			screen = indexScreen;
		} else if(request.getParameter("btn_DeleteRole") != null) {
		 // buttonFunctions.deleteRole(request, response);

		} else if(request.getParameter("addEmpRole") != null) {
		 // buttonFunctions.addEmployeeRole(request, response);

		} else if(request.getParameter("delEmpRole") != null) {
		 // buttonFunctions.deleteEmployeeRole(request, response);

		} else if(request.getParameter("btn_SaveEmployee") != null) {
			 Enumeration<String> paramNames = request.getParameterNames();
			 Map<String, String> employeeMap = new HashMap<>();
			 while(paramNames.hasMoreElements()) {
			 	String paramName = paramNames.nextElement();
			 	employeeMap.put(paramName, request.getParameter(paramName));
			 }
			 buttonFunctions.saveEmployee(employeeMap);
			 indexScreen.setEmpList(buttonFunctions.sort("dateHired"));
			 screen = indexScreen; 

		} else if(request.getParameter("btn_AddRole") != null) {
		 // buttonFunctions.addNewRole(request, response);

		} else if(request.getParameter("btn_Back") != null) {
			screen = indexScreen;
		}
		return screen;

	}





}