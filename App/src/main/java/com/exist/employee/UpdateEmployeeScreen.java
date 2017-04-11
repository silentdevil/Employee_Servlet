package com.exist.employee;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;
import java.util.stream.Collectors;
public class UpdateEmployeeScreen {
	
	public static void updateEmployee(EmployeeService empService) throws Exception {
		System.out.print("\033\143\n");
		System.out.println("Edit Employee! Input the ID\n");
		try {
			empService.getAllElements(Employee.class).forEach(System.out::println);

			Employee employee = empService.getElement(Employee.class, Long.valueOf(InputManager.getPositiveNumber("Employee ID","EMPTY_NOT_ALLOWED")));
			
			showEmployeeDetails(empService,employee);
			OUTER:
			while(true) {

				String cmd = InputManager.enterString("Action: ADDROLE, DELROLE, ADDCONTACT, DELCONTACT, BACK", "EMPTY_NOT_ALLOWED");
					switch(cmd) {
						case "ADDROLE":
							employee = FactoryService.addEmployeeRole(employee);
							break;
						case "DELROLE":
							employee = FactoryService.deleteEmployeeRole(employee);
							break;
						case "ADDCONTACT":
							employee = FactoryService.addEmployeeContact(empService,employee,"");
							break;
						case "DELCONTACT":
							employee = FactoryService.addEmployeeContact(empService,employee,"DEL");
						case "BACK":
							return;
					}

				empService.updateElement(employee);
				showEmployeeDetails(empService,employee);
			}
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
			Thread.sleep(2000);
			
		}	
	}
	
	public static void showEmployeeDetails(EmployeeService empService, Employee employee) throws Exception {
		try {
			System.out.print("\033\143");
			
			System.out.printf("Name: %s, %s %s %s\n", employee.getLastname(), employee.getFirstname(), 
				employee.getMiddlename(), employee.getSuffix());
			
			System.out.println("Address: " + employee.getAddress());
			System.out.println("Birthday: " + employee.getBirthday());
			System.out.println("GWA: " + employee.getGwa());
			System.out.println("Date hired: " + employee.getDatehired());
			System.out.println("Currently hired: " + employee.getCurrentlyHired());
			System.out.println("Contacts: " + employee.getContact());
			System.out.println("Roles: " + employee.getRoles());
		
		} catch(Exception ex) {
			ex.printStackTrace();
			//throw new Exception("Cannot find employee");
		}
	}

	public static void roleScreen(EmployeeService empService) throws Exception {
		System.out.print("\033\143");

		OUTER:
		while(true) {
			System.out.print("\033\143");
			empService.getAllElements(Role.class).forEach(System.out::println);

			System.out.println("\nWHAT TO DO [ADDROLE,[DELETEROLE,BACK]");
			String action = InputManager.enterString("Action","EMPTY_NOT_ALLOWED");

			switch(action.toUpperCase()) {
				case "ADDROLE":
					FactoryService.createRole(empService);
					break;
				case "DELETEROLE":
					FactoryService.deleteRole(empService);
					break;
				case "BACK": 
					break OUTER;
			} 
		}
	}
	
	
}