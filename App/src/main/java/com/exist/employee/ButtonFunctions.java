package com.exist.employee;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.stream.Collectors;

public class ButtonFunctions {


	private FactoryService factoryService;
	private EditEmployeeService editEmp;
	private EmployeeService empService;// = factoryService.getEmployeeService();
	private DtoMapper mapper;// = factoryService.getMapper();

	public ButtonFunctions(FactoryService factoryService, EditEmployeeService editEmp) {
		this.factoryService = factoryService;
		this.editEmp = editEmp;
		empService = factoryService.getEmployeeService();
		mapper = factoryService.getMapper();
	}

	public FactoryService getFactoryService() {
		return factoryService;
	}

	public DtoMapper getMapper() {
		return mapper;
	}

	
	private EmployeeDto employee;
	private List<Employee> empList;
	private List<Role> roleList;
	


	public ButtonFunctions(EmployeeService empService) {
		this.empService = empService;
	}

	public void editEmp(HttpServletRequest request,
	                  HttpServletResponse response) throws ServletException, IOException {

	   Employee emp = empService.findEmployeeById(Long.valueOf(request.getParameter("edit")));
	  employee = mapper.mapEmployeeDto(emp);

	  List<Role> remainingRoles = empService.getAllRoles().stream()
	    .filter(r ->!employee.getRoles().contains(mapper.mapRoleDto(r)))
	    .collect(Collectors.toList());
	  request.setAttribute("employee",employee);
	  request.setAttribute("remainingRoles",remainingRoles);
	  request.getRequestDispatcher("/editEmp.jsp").forward(request, response);

	}

	public void deleteRole(HttpServletRequest request,
	                  HttpServletResponse response) throws ServletException, IOException {
		try {
			empService.deleteElement(empService.getRoleById(Long.valueOf(request.getParameter("deleterole"))));
		      
		    request.setAttribute("ROLE_DELETE_STATUS","SUCCESS");
	  	} catch(Exception ex) {
	  		request.setAttribute("ROLE_DELETE_STATUS","FAILED");
	  	}
	  		roleList = empService.getAllElements(Role.class);
		    request.setAttribute("roleList", roleList);
	      	request.getRequestDispatcher("/modifyRoles.jsp").forward(request, response);
	}

	public void addEmployeeRole(HttpServletRequest request,
	                  HttpServletResponse response) throws ServletException, IOException {

	  RoleDto role = mapper.mapRoleDto(empService.getRoleById(Long.valueOf(request.getParameter("addEmpRole"))));
      /*employee.getContact().setLandline(request.getParameter("landline"))
                           .setMobile(request.getParameter("mobile"))
                           .setEmail(request.getParameter("email"));*/
      employee = editEmp.addEmployeeRole(employee, role);

      List<Role> remainingRoles = empService.getAllRoles().stream()
        .filter(r ->!employee.getRoles().contains(mapper.mapRoleDto(r)))
        .collect(Collectors.toList());
      request.setAttribute("employee",employee);
      request.setAttribute("remainingRoles",remainingRoles);
      request.getRequestDispatcher("/editEmp.jsp").forward(request, response);
	}

	public void deleteEmployeeRole(HttpServletRequest request,
	                  HttpServletResponse response) throws ServletException, IOException {
		RoleDto role = mapper.mapRoleDto(empService.getRoleById(Long.valueOf(request.getParameter("delEmpRole"))));

	     /* employee.getContact().setLandline(request.getParameter("landline"))
	                           .setMobile(request.getParameter("mobile"))
	                           .setEmail(request.getParameter("email"));*/
	      employee = editEmp.delEmployeeRole(employee, role);

	      List<Role> remainingRoles = empService.getAllRoles().stream()
	        .filter(r ->!employee.getRoles().contains(mapper.mapRoleDto(r)))
	        .collect(Collectors.toList());
	      request.setAttribute("employee",employee);
	      request.setAttribute("remainingRoles",remainingRoles);
	      request.getRequestDispatcher("/editEmp.jsp").forward(request, response);
	}

	public void saveChanges(HttpServletRequest request,
	                  HttpServletResponse response) throws ServletException, IOException {
		/*employee.getContact().setLandline(request.getParameter("landline"))
	                           .setMobile(request.getParameter("mobile"))
	                           .setEmail(request.getParameter("email"));*/
	    try {
	    	factoryService.createEmployee(employee);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	   	backToMainMenu(request,response);
 
	}

	public void addNewRole(HttpServletRequest request,
	                  HttpServletResponse response) throws ServletException, IOException {
	  if(request.getParameter("newrole") != "" || request.getParameter("newrole")!=null) {
		  RoleDto role = new RoleDto();
	      role.setRole(request.getParameter("newrole").toUpperCase());
	      try {
	        empService.getElement(factoryService.createRole(role));
	      } catch(Exception ex) {
	        empService.saveElement(factoryService.createRole(role));
	      }
  	  }

      roleList = empService.getAllElements(Role.class);
      request.setAttribute("roleList", roleList);
      request.getRequestDispatcher("/modifyRoles.jsp").forward(request, response);
	}

	public void addEmployee(HttpServletRequest request,
	                  HttpServletResponse response) throws ServletException, IOException {
	  try {
	  EmployeeDto employee = new EmployeeDto();
	  NameDto name = new NameDto();
      name.setLastName(request.getParameter("lastname").toUpperCase())
              .setFirstName(request.getParameter("firstname").toUpperCase())
              .setMiddleName(request.getParameter("middlename").toUpperCase())
              .setTitle(request.getParameter("title").toUpperCase())
              .setSuffix(request.getParameter("suffix").toUpperCase());
      employee.setEmployeeName(name)        
      		  .setAddress(
                new AddressDto().setStreetNo(Integer.parseInt(request.getParameter("streetno")))
                                .setStreet(request.getParameter("street").toUpperCase())
                                .setBrgy(request.getParameter("brgy").toUpperCase())
                                .setCity(request.getParameter("city").toUpperCase())
                                .setZipcode(request.getParameter("zipcode").toUpperCase()))
              .setGwa(Float.valueOf(request.getParameter("gwa")))
              .setBirthday(DatePicker.parseDate(request.getParameter("birthday")))
              .setDateHired(DatePicker.parseDate(request.getParameter("datehired")))
              .setCurrentlyHired(Boolean.valueOf(request.getParameter("currentlyhired")));
              /*.setContact(
                new ContactDto().setMobile(request.getParameter("mobile").toUpperCase())
                                .setLandline(request.getParameter("landline").toUpperCase())
                                .setEmail(request.getParameter("email"))
                                .setEmployee(employee));*/

              RoleDto role = mapper.mapRoleDto(empService.getRoleById(Long.valueOf(request.getParameter("role"))));
              employee = editEmp.addEmployeeRole(employee,role);
          } catch(Exception ex){
          	ex.printStackTrace();
          }

             backToMainMenu(request, response);
	}

	public void backToMainMenu(HttpServletRequest request,
	                  HttpServletResponse response) throws ServletException, IOException {
		empList = empService.getAllEmployees();
		request.setAttribute("empList", empList);
		request.setAttribute("addValue","");
		request.getRequestDispatcher("/index.jsp").forward(request, response);

	}

	public void viewRoleEmployees(HttpServletRequest request,
	                  HttpServletResponse response) throws ServletException, IOException {
		Role role = empService.getRoleById(Long.valueOf(request.getParameter("viewroleemp")));
		request.setAttribute("selected_role",role);
		request.getRequestDispatcher("/modifyRoles.jsp").forward(request, response);
	}

	  public static String addValue(Employee e, String id) {

	    switch(id) {
	      case "gwa":
	        return e.getGwa() + "";
	      case "datehired":
	        return e.getDateHired()+"";
	    }
	    return ""; 
  }
}




