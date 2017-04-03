package com.exist.employee;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;
public class UpdateEmployeeScreen {
	
	public static void updateEmployee(EmployeeService empService) {
		System.out.print("\033\143");
		try {
			//Employee employee = empService.searchEmployee(InputManager.getPositiveNumber("Employee ID"));
			empService.listEmployees("");
			Employee employee = showEmployeeDetails(empService);
			OUTER:
			while(true) {
				
				//System.out.println("What to do? ADDEMP, DELEMP, EDITEMP");
				String cmd = InputManager.enterString("Action: ADDROLE, DELROLE, ADDCONTACT, DELCONTACT, BACK", "EMPTY_NOT_ALLOWED");
				try {
					switch(cmd) {
						case "ADDROLE":
							employee = addEmployeeRole(empService, employee);
							break;
						case "DELROLE":
							employee = deleteEmployeeRole(empService, employee);
							break;
						case "ADDCONTACT":
							employee = addEmployeeContact(empService,employee,"");
							break;
						case "DELCONTACT":
							employee = addEmployeeContact(empService,employee,"DEL");
						case "BACK":
							break OUTER;
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				empService.updateEmployee(employee);
				showEmployeeDetails(empService);
			
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}	
	}
	
	public static Employee showEmployeeDetails(EmployeeService empService) {
		try {
			//System.out.print("\033\143");
			Employee employee = empService.searchEmployee(InputManager.getPositiveNumber("Employee ID"));
			
			System.out.printf("Name: %s, %s %s %s\n", employee.getLastname(), employee.getFirstname(), 
				employee.getMiddlename(), employee.getSuffix());
			
			System.out.println("Address: " + employee.getAddress());
			System.out.println("Birthday: " + employee.getBirthday());
			System.out.println("GWA: " + employee.getGwa());
			System.out.println("Date hired: " + employee.getDatehired());
			System.out.println("Currently hired: " + employee.getCurrentlyHired());
			System.out.println("Contacts: " + empService.searchContact(employee));
			System.out.println("Roles: " + empService.listEmployeeRoles(employee));
			
			return employee;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static void createEmployee(EmployeeService empService) {
		System.out.print("\033\143");
		Employee employee = new Employee();
		try {
		employee.setLastname(InputManager.enterString("Lastname", "EMPTY_NOT_ALLOWED"));
		employee.setFirstname(InputManager.enterString("Firstname", "EMPTY_NOT_ALLOWED"));
		employee.setMiddlename(InputManager.enterString("Middlename", "EMPTY_NOT_ALLOWED"));
		employee.setSuffix(InputManager.enterString("Suffix",""));
		employee.setTitle(InputManager.enterString("Title",""));
		employee.setAddress(createAddress(empService));
		employee.setBirthday(new Date(InputManager.getPositiveNumber("YEAR"),
				InputManager.getPositiveNumber("MONTH"), InputManager.getPositiveNumber("DAY")));
		employee.setGwa(InputManager.getPositiveFloat("GWA"));
		employee.setDatehired(new Date(InputManager.getPositiveNumber("YEAR"),
				InputManager.getPositiveNumber("MONTH"), InputManager.getPositiveNumber("DAY")));
		employee.setCurrentlyHired(InputManager.getBoolean("CURRENTLY HIRED"));
		employee.setContact(createContact(empService));
		employee.setRoles(new HashSet<Role>());
		employee.getRoles().add(setRoleToEmployee(empService));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		empService.addEmployee(employee);
		empService.listEmployees("");
		
		
	}
	
	private static Address createAddress(EmployeeService empService) {
		Address address = new Address();
		try {
			address.setStreetno(InputManager.getPositiveNumber("Street no"));
			address.setStreet(InputManager.enterString("Street","EMPTY_NOT_ALLOWED"));
			address.setBrgy(InputManager.enterString("Brgy", "EMPTY_NOT_ALLOWED"));
			address.setCity(InputManager.enterString("City", "EMPTY_NOT_ALLOWED"));
			address.setZipcode(InputManager.enterString("Zipcode","EMPTY_NOT_ALLOWED"));
		} catch(Exception ex) {
			ex.printStackTrace();
			
		}
		empService.addAddress(address);
		return address;
	}
	
	private static Contact createContact(EmployeeService empService) {
		Contact contact = new Contact();
		try {
			contact.setLandline(InputManager.enterString("Landline","EMPTY_NOT_ALLOWED"));
			contact.setMobile(InputManager.enterString("Mobile","EMPTY_NOT_ALLOWED"));
			contact.setEmail(InputManager.enterString("Email","EMPTY_NOT_ALLOWED"));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		empService.addContact(contact);
		return contact;
	}
	
	/*private static Role createRole(EmployeeService empService) {
		Role role = new Role();
		
		
	}*/
	
	private static Role setRoleToEmployee(EmployeeService empService) {
		System.out.println("What role: ");
		empService.listRoles();
		try {
			Role role = empService.searchRole(InputManager.enterString("ROLE","EMPTY_NOT_ALLOWED"));
			return role;
		} catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	public static Employee addEmployeeRole(EmployeeService empService, Employee employee) {
		Set<Role> roles = empService.listEmployeeRoles(employee);
		roles.add(setRoleToEmployee(empService));	
		employee.setRoles(roles);
		return employee;
	}

	public static Employee deleteEmployeeRole(EmployeeService empService, Employee employee) {
		Set<Role> roles = empService.listEmployeeRoles(employee);
		Role role = empService.searchRole(InputManager.enterString("ROLE","EMPTY_NOT_ALLOWED"));
		roles.remove(role);	
		employee.setRoles(roles);
		return employee;
	}

	public static Employee addEmployeeContact(EmployeeService empService, Employee employee, String id) {
		System.out.print("\033\143");
		String command = InputManager.enterString("Contact info: [LANDLINE, MOBILE, EMAIL","EMPTY_NOT_ALLOWED");
		Contact contact = empService.searchContact(employee);
		if(contact == null) {
			contact = new Contact();
		}
		try {
			switch(command) {
				case "LANDLINE":
					contact.setLandline(!id.equals("DEL") ? InputManager.enterString("Landline","EMPTY_IS_ALLOWED") : "");
					break;
				case "MOBILE":
					contact.setMobile(!id.equals("DEL") ? InputManager.enterString("Mobile","EMPTY_IS_ALLOWED"):"");
					break;
				case "EMAIL":
					contact.setEmail(!id.equals("DEL") ? InputManager.enterString("Email","EMPTY_IS_ALLOWED"): "");
					break;

			}
			employee.setContact(empService.addContact(contact));
		} catch(Exception ex){ex.printStackTrace();}
		return employee;
	}

	public static void createRole(EmployeeService empService) {
		System.out.print("\033\143");
		empService.listRoles();
		Role role = new Role();
		role.setRole(InputManager.enterString("NEW ROLE","EMPTY_NOT_ALLOWED"));
		empService.addRole(role);
	}

	public static void deleteRole(EmployeeService empService) {
		System.out.print("\033\143");
		empService.listRoles();
		Role role = empService.searchRole(InputManager.enterString("ROLE TO DELETE","EMPTY_NOT_ALLOWED"));
		if(empService.isRoleDeletable(role))
			empService.deleteRole(role);
	}

	public static void roleScreen(EmployeeService empService) {
		System.out.print("\033\143");

		OUTER:
		while(true) {
			System.out.print("\033\143");
			empService.listRoles();

			System.out.println("\nWHAT TO DO [ADDROLE,[DELETEROLE,BACK]");
			String action = InputManager.enterString("Action","EMPTY_NOT_ALLOWED");

			switch(action) {
				case "ADDROLE":
					createRole(empService);
					break;
				case "DELETEROLE":
					deleteRole(empService);
					break;
				case "BACK": 
					break OUTER;
			} 
		}
	}
	
	
}