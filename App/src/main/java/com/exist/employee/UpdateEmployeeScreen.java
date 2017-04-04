package com.exist.employee;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;
public class UpdateEmployeeScreen {
	
	public static void updateEmployee(EmployeeService empService) {
		System.out.print("\033\143");
		try {
			empService.listEmployees("");

			Employee employee = empService.getData(Long.valueOf(InputManager.getPositiveNumber("Employee ID")),new Employee());
			
			showEmployeeDetails(empService,employee);
			OUTER:
			while(true) {

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
							return;
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
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
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		//return null;
	}
	
	public static void createEmployee(EmployeeService empService) {
		System.out.print("\033\143");
		System.out.println("CREATE NEW EMPLOYEE!\n\n");
		Employee employee = new Employee();
		try {
		employee.setLastname(InputManager.enterString("Lastname", "EMPTY_NOT_ALLOWED").toUpperCase());
		employee.setFirstname(InputManager.enterString("Firstname", "EMPTY_NOT_ALLOWED").toUpperCase());
		employee.setMiddlename(InputManager.enterString("Middlename", "EMPTY_NOT_ALLOWED").toUpperCase());
		employee.setSuffix(InputManager.enterString("Suffix","").toUpperCase());
		employee.setTitle(InputManager.enterString("Title","").toUpperCase());
		employee.setAddress(createAddress(empService));
		employee.setBirthday(DatePicker.parseDate(InputManager.enterString("BirthDate [YYYY-MM-DD]", "EMPTY_NOT_ALLOWED")));
		employee.setGwa(InputManager.getPositiveFloat("GWA"));
		employee.setDatehired(DatePicker.parseDate(InputManager.enterString("Hire Date [YYYY-MM-DD]", "EMPTY_NOT_ALLOWED")));
		employee.setCurrentlyHired(InputManager.getBoolean("CURRENTLY HIRED"));
		employee.setContact(createContact(empService));
		employee.setRoles(new HashSet<Role>());
		employee.getRoles().add(setRoleToEmployee(empService));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		empService.saveElement(employee);
		//empService.listEmployees("");
	}
	
	private static Address createAddress(EmployeeService empService) {
		Address address = new Address();
		try {
			address.setStreetno(InputManager.getPositiveNumber("Street no"));
			address.setStreet(InputManager.enterString("Street","EMPTY_NOT_ALLOWED").toUpperCase());
			address.setBrgy(InputManager.enterString("Brgy", "EMPTY_NOT_ALLOWED").toUpperCase());
			address.setCity(InputManager.enterString("City", "EMPTY_NOT_ALLOWED").toUpperCase());
			address.setZipcode(InputManager.enterString("Zipcode","EMPTY_NOT_ALLOWED").toUpperCase());
		} catch(Exception ex) {
			ex.printStackTrace();
			
		}
		empService.saveElement(address);
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
		
		empService.saveElement(contact);
		return contact;
	}

	private static Role setRoleToEmployee(EmployeeService empService) {
		System.out.println("What role: ");
		empService.listRoles();
		try {
			Role role = empService.getData(Long.valueOf(InputManager.getPositiveNumber("ROLE")),new Role());
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

	public static Employee deleteEmployeeRole(EmployeeService empService, Employee employee) throws Exception {
		Set<Role> roles = empService.listEmployeeRoles(employee);
		Role role = empService.getData(Long.valueOf(InputManager.getPositiveNumber("ROLE")),new Role());
		roles.remove(role);	
		employee.setRoles(roles);
		return employee;
	}

	public static Employee addEmployeeContact(EmployeeService empService, Employee employee, String id) {
		System.out.print("\033\143");
		String command = InputManager.enterString("Contact info: [LANDLINE, MOBILE, EMAIL","EMPTY_NOT_ALLOWED");
		Contact contact = empService.getData(employee.getContact().getContactId(), new Contact());
		if(contact == null) {
			contact = new Contact();
		}
		try {
			switch(command.toUpperCase()) {
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
			empService.saveElement(contact);
			employee.setContact(contact);
		} catch(Exception ex){ex.printStackTrace();}
		return employee;
	}

	public static void createRole(EmployeeService empService) {
		System.out.print("\033\143");
		empService.listRoles();
		Role role = new Role();
		role.setRole(InputManager.enterString("NEW ROLE","EMPTY_NOT_ALLOWED").toUpperCase());
		empService.saveElement(role);
	}

	public static void deleteRole(EmployeeService empService) throws Exception {
		System.out.print("\033\143");
		empService.listRoles();
		Role role = empService.getData(Long.valueOf(InputManager.getPositiveNumber("ROLE ID")), new Role());
		if(empService.isRoleDeletable(role))
			empService.deleteElement(role);
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