package com.exist.employee;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.Scanner;
//import com.exist.employee.InputManager;

public class Main {
	public static void main(String[] args) {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		EmployeeService empServ = new EmployeeService();
		String order = "";
		while(true) {
			System.out.print("\033\143");
			empServ.listEmployees(order);
			//System.out.println("What to do? ADDEMP, DELEMP, EDITEMP");
			String cmd = InputManager.enterString("Action: ADDEMP, DELEMP, EDITEMP, SORT_GWA, MODIFYROLES",
			 "EMPTY_NOT_ALLOWED");
			try {
				switch(cmd) {
					case "ADD":
						FactoryService.createEmployee(empServ);
						break;
						
					case "DELEMP":
						empServ.deleteElement(empServ.getData(InputManager.getPositiveNumber("Employee ID","EMPTY_NOT_ALLOWED"),new Employee()));
						break;
						
					case "EDITEMP":
						UpdateEmployeeScreen.updateEmployee(empServ);
						break;
					case "MODIFYROLES":
						UpdateEmployeeScreen.roleScreen(empServ);
						break;
					case "SORT_GWA":
						order = "ORDER BY gwa";
						break;
					case "SORT_HIREDATE":
						order = "ORDER BY datehired";
						break;
					case "SORT_LASTNAME":
						order = "ORDER BY lastname";
						break;
				}
			} catch (Exception ex) {
				System.err.println(ex);
				InputManager.enterString("","");
			}
			
			//empServ.listEmployees();
		
		}
		
		
		
		
		
	}
}