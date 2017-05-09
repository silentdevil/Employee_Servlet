// Import required java libraries
package com.exist.employee;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeServlet extends HttpServlet {
 

  private ButtonFunctions buttonFunctions = new ButtonFunctions(
                      new FactoryService
                          (new MappedService
                            (new EmployeeService(),new DtoMapper())), 
                      new EditEmployeeService());

  private EmployeeService empService = buttonFunctions.getFactoryService().getMappedService().getEmployeeService();
  private IndexScreenImpl indexScreen = new IndexScreenImpl();
  private EditEmployeeScreenImpl editEmployeeScreen = new EditEmployeeScreenImpl();
  private ModifyRoleScreenImpl modifyRoleScreen = new ModifyRoleScreenImpl();
  private EmployeeRegisterScreenImpl employeeRegisterScreen = new EmployeeRegisterScreenImpl();
  private Screen[] screens = {indexScreen, editEmployeeScreen, 
                employeeRegisterScreen, modifyRoleScreen};
  private View view;

  private ButtonHandler buttonHandler = new ButtonHandler(buttonFunctions, screens);

  public void doGet(HttpServletRequest request,
                  HttpServletResponse response) throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    view = new IndexViewImpl(indexScreen.setEmpList(empService.getAllEmployees("employeeName")));
    out.print(view.publish());

  }

  public void doPost(HttpServletRequest request,
                  HttpServletResponse response) throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    
    view.setScreen(buttonHandler.handle(request, response));
    out.print(view.publish());
   
  }

}