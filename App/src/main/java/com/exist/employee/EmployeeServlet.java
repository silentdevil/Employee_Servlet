// Import required java libraries
package com.exist.employee;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeServlet extends HttpServlet {
 

  private ButtonFunctions buttonFunctions = new ButtonFunctions(
                      new FactoryService(new EmployeeService(), new DtoMapper()), new EditEmployeeService());

  private EmployeeService empServ = buttonFunctions.getFactoryService().getEmployeeService();

  private List<Employee> empList;
  public void doGet(HttpServletRequest request,
                  HttpServletResponse response) throws ServletException, IOException {
   
    empList = empServ.getAllEmployees();
  	request.setAttribute("empList", empList);
    request.setAttribute("addValue","");
  	request.getRequestDispatcher("/index.jsp").forward(request, response);
 
  }

  public void doPost(HttpServletRequest request,
                  HttpServletResponse response) throws ServletException, IOException {

    List<Role> roleList = empServ.getAllRoles();

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
      buttonFunctions.editEmp(request, response);

    } else if(request.getParameter("modifyroles") != null) {
      request.setAttribute("roleList", roleList);
      request.setAttribute("selected_role",new Role());
      request.getRequestDispatcher("/modifyRoles.jsp").forward(request, response);

    } else if(request.getParameter("delete") != null) {
      empServ.deleteElement(empServ.getElement(Employee.class,
        Long.valueOf(request.getParameter("delete"))));
      request.setAttribute("empList", empList); request.setAttribute("addValue","");
      request.getRequestDispatcher("/index.jsp").forward(request, response);

    } else if(request.getParameter("deleterole") != null) {
      buttonFunctions.deleteRole(request, response);

    } else if(request.getParameter("addEmpRole") != null) {
      buttonFunctions.addEmployeeRole(request, response);

    } else if(request.getParameter("delEmpRole") != null) {
      buttonFunctions.deleteEmployeeRole(request, response);

    } else if(request.getParameter("savechanges") != null) {
      buttonFunctions.saveChanges(request, response);  
    
    } else if(request.getParameter("addnewrole") != null) {
      buttonFunctions.addNewRole(request, response);

    } else if(request.getParameter("addemployee") != null) {
      buttonFunctions.addEmployee(request, response);

    } else if(request.getParameter("back") != null) {
      buttonFunctions.backToMainMenu(request, response);

    } else if(request.getParameter("viewroleemp") != null) {
      buttonFunctions.viewRoleEmployees(request, response);
    }



  }

}