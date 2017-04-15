// Import required java libraries
package com.exist.employee;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
// Extend HttpServlet class
public class HelloWorld extends HttpServlet {
 
  private String message;
   EmployeeService empServ = new EmployeeService();
   //UpdateEmployeeScreen updateEmployeeScreen = new UpdateEmployeeScreen();

  public void init() throws ServletException
  {
      // Do required initialization
      message = "Hello World";
  }

  public void doGet(HttpServletRequest request,
                  HttpServletResponse response) throws ServletException, IOException {
    List<Employee> empList = empServ.getAllElements(Employee.class);
	
	request.setAttribute("empList", empList);
	request.getRequestDispatcher("/index.jsp").forward(request, response);


	 
  }
  
  public void destroy()
  {
      // do nothing.
  }
}