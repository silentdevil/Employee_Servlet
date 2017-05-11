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
			EmployeeDto employee = buttonFunctions.addEmployee();
			employeeRegisterScreen.setEmployee(employee);
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
			editEmployeeScreen.setEmployee(employee, buttonFunctions.getAllRoles());
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
			buttonFunctions.deleteRole(Long.valueOf(request.getParameter("btn_DeleteRole")));
			modifyRoleScreen.setRoleList(buttonFunctions.getAllRoles());
			screen = modifyRoleScreen;

		} else if(request.getParameter("btn_AddEmployeeRole") != null) {
			long roleId = Long.valueOf(request.getParameter("drp_EmployeeRole"));
			EmployeeDto employee = buttonFunctions.addEmployeeRole(roleId);
			editEmployeeScreen.setEmployee(employee, buttonFunctions.getAllRoles());
			screen = editEmployeeScreen;

		 } else if(request.getParameter("btn_AddEmployeeContact") != null) {
			if(!request.getParameter("employee_ContactInfo").isEmpty()) {
				ContactDto contact = new ContactDto();
				contact.setContactType(request.getParameter("employee_ContactType"));
				contact.setContactInfo(request.getParameter("employee_ContactInfo"));
				EmployeeDto employee = buttonFunctions.addEmployeeContact(contact);
				editEmployeeScreen.setEmployee(employee, buttonFunctions.getAllRoles());
				screen = editEmployeeScreen;
			}
		
		} else if(request.getParameter("btn_DeleteEmployeeContact") != null) {
			long contactId = Long.valueOf(request.getParameter("btn_DeleteEmployeeContact"));
			EmployeeDto employee = buttonFunctions.deleteEmployeeContact(contactId);
			editEmployeeScreen.setEmployee(employee, buttonFunctions.getAllRoles());
			screen = editEmployeeScreen;
			
		} else if(request.getParameter("btn_DeleteEmployeeRole") != null) {
			long roleId = Long.valueOf(request.getParameter("btn_DeleteEmployeeRole"));
			EmployeeDto employee = buttonFunctions.deleteEmployeeRole(roleId);
			editEmployeeScreen.setEmployee(employee, buttonFunctions.getAllRoles());
			screen = editEmployeeScreen;

		} else if(request.getParameter("btn_UpdateEmployee") != null) {
			buttonFunctions.updateEmployee(getMapfromServlet(request, response));
			 indexScreen.setEmpList(buttonFunctions.sort("dateHired"));
			 screen = indexScreen; 

		}else if(request.getParameter("btn_SaveEmployee") != null) {
			 buttonFunctions.saveEmployee(getMapfromServlet(request,response));
			 indexScreen.setEmpList(buttonFunctions.sort("dateHired"));
			 screen = indexScreen; 

		} else if(request.getParameter("btn_AddNewRole") != null) {
			buttonFunctions.addNewRole(buttonFunctions.getAllRoles(),
				request.getParameter("txt_AddNewRole"));
			modifyRoleScreen.setRoleList(buttonFunctions.getAllRoles());
			screen = modifyRoleScreen;

		} else if(request.getParameter("btn_EditRole") != null) {
			long id = Long.valueOf(request.getParameter("btn_EditRole"));
			buttonFunctions.editRole(id, request.getParameter("txt_EditRole_"+id));
			modifyRoleScreen.setRoleList(buttonFunctions.getAllRoles());
			screen = modifyRoleScreen;

		} else if(request.getParameter("btn_OutputRole_Employees") != null) { 
			long roleId = Long.valueOf(request.getParameter("btn_OutputRole_Employees"));
			RoleDto role = buttonFunctions.outputEmployees(roleId);
			modifyRoleScreen.setSelectedRole(role);
			modifyRoleScreen.setRoleList(buttonFunctions.getAllRoles());
			screen = modifyRoleScreen;

		} else if(request.getParameter("btn_RegEmpContact") != null) { 
			ContactDto contact = new ContactDto();
			contact.setContactType(request.getParameter("employee_ContactType"));
			contact.setContactInfo(request.getParameter("employee_ContactInfo"));
			EmployeeDto employee = buttonFunctions.regEmpAddContact(contact, 
										getMapfromServlet(request, response));
			employeeRegisterScreen.setRoleList(buttonFunctions.getAllRoles());
			employeeRegisterScreen.setEmployee(employee);
			screen = employeeRegisterScreen;

		} else if(request.getParameter("btn_Back") != null) {
			screen = indexScreen;

		} else if(request.getParameter("btn_RegEmpRole") != null) { 
			long roleId = Long.valueOf(request.getParameter("employee_Role"));
			EmployeeDto employee = buttonFunctions.regEmpAddRole(roleId, 
										getMapfromServlet(request, response));
			employeeRegisterScreen.setRoleList(buttonFunctions.getAllRoles());
			employeeRegisterScreen.setEmployee(employee);
			screen = employeeRegisterScreen;

		} else if(request.getParameter("btn_RegEmpRoleDelete") != null) {
			long roleId = Long.valueOf(request.getParameter("btn_RegEmpRoleDelete"));
			EmployeeDto employee = buttonFunctions.regEmpDeleteRole(roleId, 
										getMapfromServlet(request, response));
			employeeRegisterScreen.setRoleList(buttonFunctions.getAllRoles());
			employeeRegisterScreen.setEmployee(employee);
			screen = employeeRegisterScreen;
		}
		return screen;

	}

	public  Map<String, String> getMapfromServlet(HttpServletRequest request,
	                  HttpServletResponse response) throws ServletException, IOException { 
		Enumeration<String> paramNames = request.getParameterNames();
		 Map<String, String> employeeMap = new HashMap<>();
		 while(paramNames.hasMoreElements()) {
		 	String paramName = paramNames.nextElement();
		 	employeeMap.put(paramName, request.getParameter(paramName));
		 }
		 return employeeMap;
	}

}