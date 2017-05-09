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

  private EmployeeService empService = buttonFunctions.getFactoryService().getEmployeeService();
  private View view;

  public void doGet(HttpServletRequest request,
                  HttpServletResponse response) throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    view = new IndexViewImpl(new IndexScreenImpl(empService.getAllEmployees("employeeName")));
    out.print(view.publish());

  }

  public void doPost(HttpServletRequest request,
                  HttpServletResponse response) throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    
    if(request.getParameter("edit") != null) {
      EmployeeDto employee = buttonFunctions.getMapper()
              .mapEmployeeDto(empService.findEmployeeById(Long.valueOf(request.getParameter("edit"))));
      view.setScreen(new EditEmployeeScreenImpl(employee));
    } else if(request.getParameter("addEmp") != null) {
      view.setScreen(new EmployeeRegisterScreenImpl());
    }
    out.print(view.publish());
   
  }

}