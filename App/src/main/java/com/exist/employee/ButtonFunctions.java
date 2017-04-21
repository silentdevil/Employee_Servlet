package com.exist.employee;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.stream.Collectors;

public class ButtonFunctions {


	private EmployeeService empServ;
	private FactoryService factoryService = new FactoryService();
	private DtoMapper mapper = new DtoMapper();
	private EmployeeDto employee;
	private List<Employee> empList;
	private List<Role> roleList;
	private EditEmployeeService editEmp = new EditEmployeeService();


	public ButtonFunctions(EmployeeService empServ) {
		this.empServ = empServ;
	}

	public void editEmp(HttpServletRequest request,
	                  HttpServletResponse response) throws ServletException, IOException {

	   Employee emp = empServ.getElement(Employee.class, 
	              Long.valueOf(request.getParameter("edit")));
	  employee = mapper.mapEmployeeDto(emp);

	  List<Role> remainingRoles = empServ.getAllElements(Role.class).stream()
	    .filter(r ->!employee.getRoles().contains(mapper.mapRoleDto(r)))
	    .collect(Collectors.toList());
	  request.setAttribute("employee",employee);
	  request.setAttribute("remainingRoles",remainingRoles);
	  request.getRequestDispatcher("/editEmp.jsp").forward(request, response);

	}

	public void deleteRole(HttpServletRequest request,
	                  HttpServletResponse response) throws ServletException, IOException {
		try {
			empServ.deleteElement(empServ.getElement(Role.class,
		        Long.valueOf(request.getParameter("deleterole"))));
		      
		    request.setAttribute("ROLE_DELETE_STATUS","SUCCESS");
	  	} catch(Exception ex) {
	  		request.setAttribute("ROLE_DELETE_STATUS","FAILED");
	  	}
	  		roleList = empServ.getAllElements(Role.class);
		    request.setAttribute("roleList", roleList);
	      	request.getRequestDispatcher("/modifyRoles.jsp").forward(request, response);
	}

	public void addEmployeeRole(HttpServletRequest request,
	                  HttpServletResponse response) throws ServletException, IOException {

	  RoleDto role = mapper.mapRoleDto(empServ.getElement(Role.class,
        Long.valueOf(request.getParameter("addEmpRole"))));
      employee.getContact().setLandline(request.getParameter("landline"))
                           .setMobile(request.getParameter("mobile"))
                           .setEmail(request.getParameter("email"));
      employee = editEmp.addEmployeeRole(employee, role);

      List<Role> remainingRoles = empServ.getAllElements(Role.class).stream()
        .filter(r ->!employee.getRoles().contains(mapper.mapRoleDto(r)))
        .collect(Collectors.toList());
      request.setAttribute("employee",employee);
      request.setAttribute("remainingRoles",remainingRoles);
      request.getRequestDispatcher("/editEmp.jsp").forward(request, response);
	}

	public void deleteEmployeeRole(HttpServletRequest request,
	                  HttpServletResponse response) throws ServletException, IOException {
		RoleDto role = mapper.mapRoleDto(empServ.getElement(Role.class,
	        Long.valueOf(request.getParameter("delEmpRole"))));

	      employee.getContact().setLandline(request.getParameter("landline"))
	                           .setMobile(request.getParameter("mobile"))
	                           .setEmail(request.getParameter("email"));
	      employee = editEmp.delEmployeeRole(employee, role);

	      List<Role> remainingRoles = empServ.getAllElements(Role.class).stream()
	        .filter(r ->!employee.getRoles().contains(mapper.mapRoleDto(r)))
	        .collect(Collectors.toList());
	      request.setAttribute("employee",employee);
	      request.setAttribute("remainingRoles",remainingRoles);
	      request.getRequestDispatcher("/editEmp.jsp").forward(request, response);
	}

	public void saveChanges(HttpServletRequest request,
	                  HttpServletResponse response) throws ServletException, IOException {
		employee.getContact().setLandline(request.getParameter("landline"))
	                           .setMobile(request.getParameter("mobile"))
	                           .setEmail(request.getParameter("email"));
	    factoryService.updateDto(factoryService.createEmployee(employee));
	   	backToMainMenu(request,response);
 
	}

	public void addNewRole(HttpServletRequest request,
	                  HttpServletResponse response) throws ServletException, IOException {
	  if(request.getParameter("newrole") != "" || request.getParameter("newrole")!=null) {
		  RoleDto role = new RoleDto();
	      role.setRole(request.getParameter("newrole").toUpperCase());
	      try {
	        empServ.getElement(factoryService.createRole(role));
	      } catch(Exception ex) {
	        empServ.saveElement(factoryService.createRole(role));
	      }
  	  }

      roleList = empServ.getAllElements(Role.class);
      request.setAttribute("roleList", roleList);
      request.getRequestDispatcher("/modifyRoles.jsp").forward(request, response);
	}

	public void addEmployee(HttpServletRequest request,
	                  HttpServletResponse response) throws ServletException, IOException {
		
	  EmployeeDto employee = new EmployeeDto();
      employee.setLastname(request.getParameter("lastname").toUpperCase())
              .setFirstname(request.getParameter("firstname").toUpperCase())
              .setMiddlename(request.getParameter("middlename").toUpperCase())
              .setTitle(request.getParameter("title").toUpperCase())
              .setSuffix(request.getParameter("suffix").toUpperCase())
              .setAddress(
                new AddressDto().setStreetno(Integer.parseInt(request.getParameter("streetno")))
                                .setStreet(request.getParameter("street").toUpperCase())
                                .setBrgy(request.getParameter("brgy").toUpperCase())
                                .setCity(request.getParameter("city").toUpperCase())
                                .setZipcode(request.getParameter("zipcode").toUpperCase()))
              .setGwa(Float.valueOf(request.getParameter("gwa")))
              .setBirthday(DatePicker.parseDate(request.getParameter("birthday")))
              .setDatehired(DatePicker.parseDate(request.getParameter("datehired")))
              .setCurrentlyHired(Boolean.valueOf(request.getParameter("currentlyhired")))
              .setContact(
                new ContactDto().setMobile(request.getParameter("mobile").toUpperCase())
                                .setLandline(request.getParameter("landline").toUpperCase())
                                .setEmail(request.getParameter("email"))
                                .setEmployee(employee));

              RoleDto role = mapper.mapRoleDto(empServ.getElement(Role.class,
                              Long.valueOf(request.getParameter("role"))));
              employee = editEmp.addEmployeeRole(employee,role);

              empServ.saveElement(factoryService.createEmployee(employee));

             backToMainMenu(request, response);
	}

	public void backToMainMenu(HttpServletRequest request,
	                  HttpServletResponse response) throws ServletException, IOException {
		empList = empServ.getAllElements(Employee.class);
		request.setAttribute("empList", empList);
		request.setAttribute("addValue","");
		request.getRequestDispatcher("/index.jsp").forward(request, response);

	}

	  public static String addValue(Employee e, String id) {

	    switch(id) {
	      case "gwa":
	        return e.getGwa() + "";
	      case "datehired":
	        return e.getDatehired()+"";
	    }
	    return ""; 
  }
}




