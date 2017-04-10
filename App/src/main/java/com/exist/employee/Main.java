package com.exist.employee;

import java.util.logging.Level;

public class Main {
	public static void main(String[] args) throws Exception {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		EmployeeService empServ = new EmployeeService();
		String order = "";
		OUTER:
		while(true) {
			System.out.print("\033\143");
			empServ.listEmployees(order);
			String cmd = InputManager.enterString("Action: ADDEMP, DELEMP, EDITEMP, MODIFYROLES\n SORT_GWA, SORT_HIREDATE, SORT_LASTNAME",
			 "EMPTY_NOT_ALLOWED");
			try {
				switch(cmd.toUpperCase()) {
					case "ADDEMP":
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
					case "EXIT":
						break OUTER;
				}
			} catch (Exception ex) {
				InputManager.output(ex.toString());
			}
		}
	}
}