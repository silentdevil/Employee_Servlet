// Import required java libraries
package com.exist.employee;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.stream.Collectors;
// Extend HttpServlet class
public class EmployeeServlet extends HttpServlet {
 
  private String message;
   EmployeeService empServ = new EmployeeService();
   List<Employee> empList;
   DtoMapper mapper = new DtoMapper();
   FactoryService factoryService = new FactoryService();
   //UpdateEmployeeScreen updateEmployeeScreen = new UpdateEmployeeScreen();

  public void init() throws ServletException
  {
      // Do required initialization
      message = "Hello World";
  }

  public void doGet(HttpServletRequest request,
                  HttpServletResponse response) throws ServletException, IOException {
    List<Employee> empList = empServ.getAllElements(Employee.class);
    
    //System.out.println(roleList);
	
	request.setAttribute("empList", empList);
  //request.setAttribute("roleList", roleList);
  request.setAttribute("addValue","");
	request.getRequestDispatcher("/index.jsp").forward(request, response);
  //request.getRequestDispatcher("/empRegister.jsp").forward(request, response);


	 
  }

  public void doPost(HttpServletRequest request,
                  HttpServletResponse response) throws ServletException, IOException {
    //response.sendRedirect()
    List<Role> roleList = empServ.getAllElements(Role.class);
    request.setAttribute("roleList", roleList);
    
    
    if(request.getParameter("addEmp")!=null) {
      request.getRequestDispatcher("/empRegister.jsp").forward(request, response);
    } else if(request.getParameter("sort_gwa")!=null) {
      empList = empServ.getAllElements(Employee.class,"gwa");
      request.setAttribute("addValue","gwa");request.setAttribute("empList", empList);
      request.getRequestDispatcher("/index.jsp").forward(request, response);
    } else if(request.getParameter("sort_date")!=null) {
      empList = empServ.getAllElements(Employee.class,"datehired");
      request.setAttribute("addValue","datehired");request.setAttribute("empList", empList);
      request.getRequestDispatcher("/index.jsp").forward(request, response);
    } else if(request.getParameter("sort_lastname")!=null) {
      request.setAttribute("addValue","");
      empList = empServ.getAllElements(Employee.class,"lastname");request.setAttribute("empList", empList);
      request.getRequestDispatcher("/index.jsp").forward(request, response);
    } else if(request.getParameter("edit") != null) {
      Employee emp = empServ.getElement(Employee.class, 
              Long.valueOf(request.getParameter("edit")));
      EmployeeDto employee = mapper.mapEmployeeDto(emp);

      List<Role> remainingRoles = empServ.getAllElements(Role.class).stream()
        .filter(r ->!employee.getRoles().contains(mapper.mapRoleDto(r)))
        .collect(Collectors.toList());
      request.setAttribute("employee",employee);
      request.setAttribute("remainingRoles",remainingRoles);
      request.getRequestDispatcher("/editEmp.jsp").forward(request, response);
    } else if(request.getParameter("modifyroles") != null) {
      request.getRequestDispatcher("/modifyRoles.jsp").forward(request, response);
    } else if(request.getParameter("delete") != null) {
      empServ.deleteElement(empServ.getElement(Employee.class,
        Long.valueOf(request.getParameter("delete"))));
      request.getRequestDispatcher("/index.jsp").forward(request, response);
    } else if(request.getParameter("deleterole") != null) {
      empServ.deleteElement(empServ.getElement(Role.class,
        Long.valueOf(request.getParameter("deleterole"))));
      request.getRequestDispatcher("/modifyroles.jsp").forward(request, response);
    } 


  }
  
  public void destroy()
  {
      // do nothing.
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