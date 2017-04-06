package com.exist.employee;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;
import java.util.stream.Collectors;
public class UpdateEmployeeScreen {
	
	public static void updateEmployee(EmployeeService empService) {
		System.out.print("\033\143");
		try {
			empService.listEmployees("");

			Employee employee = empService.getData(Long.valueOf(InputManager.getPositiveNumber("Employee ID","EMPTY_NOT_ALLOWED")),new Employee());
			
			showEmployeeDetails(empService,employee);
			OUTER:
			while(true) {

				String cmd = InputManager.enterString("Action: ADDROLE, DELROLE, ADDCONTACT, DELCONTACT, BACK", "EMPTY_NOT_ALLOWED");
				//try {
					switch(cmd) {
						case "ADDROLE":
							employee = FactoryService.addEmployeeRole(empService, employee);
							break;
						case "DELROLE":
							employee = FactoryService.deleteEmployeeRole(empService, employee);
							break;
						case "ADDCONTACT":
							employee = FactoryService.addEmployeeContact(empService,employee,"");
							break;
						case "DELCONTACT":
							employee = FactoryService.addEmployeeContact(empService,employee,"DEL");
						case "BACK":
							return;
					}
			//	} catch (Exception ex) {
			//		ex.printStackTrace();
			//	}
				empService.saveElement(employee);
				showEmployeeDetails(empService,employee);
			
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}	
	}
	
	public static void showEmployeeDetails(EmployeeService empService, Employee employee) {
		try {
			System.out.print("\033\143");
			//Employee employee = empService.searchEmployee(InputManager.getPositiveNumber("Employee ID"));
			
			System.out.printf("Name: %s, %s %s %s\n", employee.getLastname(), employee.getFirstname(), 
				employee.getMiddlename(), employee.getSuffix());
			
			System.out.println("Address: " + empService.getData(employee.getAddress().getAddressId(),
											new Address()));
			System.out.println("Birthday: " + employee.getBirthday());
			System.out.println("GWA: " + employee.getGwa());
			System.out.println("Date hired: " + employee.getDatehired());
			System.out.println("Currently hired: " + employee.getCurrentlyHired());
			System.out.println("Contacts: " + empService.getData(employee.getContact().getContactId(),
											new Contact()));
			System.out.println("Roles: " + empService.listEmployeeRoles(employee));
			
			//return employee;

		//employee.getRoles().stream().map(role -> empService.getData(role.getRoleId(), ))
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		//return null;
	}

	public static void roleScreen(EmployeeService empService) throws Exception {
		System.out.print("\033\143");

		OUTER:
		while(true) {
			System.out.print("\033\143");
			empService.listRoles();

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