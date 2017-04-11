package com.exist.employee;
import java.util.*;
public class FactoryService {

	private static EmployeeService empService = new EmployeeService();
	
	public static void createEmployee() throws Exception {
		System.out.print("\033\143");
		System.out.println("CREATE NEW EMPLOYEE!\n\n");
		Employee employee = new Employee();
		String id = "EMPTY_NOT_ALLOWED";
		try {
			employee.setLastname(InputManager.enterString("Lastname", id).toUpperCase());
			employee.setFirstname(InputManager.enterString("Firstname", id).toUpperCase());
			employee.setMiddlename(InputManager.enterString("Middlename", id).toUpperCase());
			employee.setSuffix(InputManager.enterString("Suffix","").toUpperCase());
			employee.setTitle(InputManager.enterString("Title","").toUpperCase());
			employee.setAddress(createAddress());
			employee.setBirthday(DatePicker.parseDate(InputManager.enterString("Date YYYY-MM-DD", id),id));
			employee.setGwa(InputManager.getPositiveFloat("GWA",id));
			employee.setDatehired(DatePicker.parseDate(InputManager.enterString("Hire Date [YYYY-MM-DD]", id),id));
			employee.setCurrentlyHired(InputManager.getBoolean("CURRENTLY HIRED"));
			employee.setContact(createContact());
			employee.setRoles(new HashSet<Role>());
			employee.getRoles().add(setRoleToEmployee());
		
		empService.saveElement(employee);
		} catch(Exception ex) {
			throw new Exception("Cannot create employee");
		}
	}
	
	private static Address createAddress() throws Exception {
		System.out.println("Enter ADDRESS!");
		Address address = new Address();
		try {
			address.setStreetno(InputManager.getPositiveNumber("Street no","EMPTY_NOT_ALLOWED"));
			address.setStreet(InputManager.enterString("Street","EMPTY_NOT_ALLOWED").toUpperCase());
			address.setBrgy(InputManager.enterString("Brgy", "EMPTY_NOT_ALLOWED").toUpperCase());
			address.setCity(InputManager.enterString("City", "EMPTY_NOT_ALLOWED").toUpperCase());
			address.setZipcode(InputManager.enterString("Zipcode","EMPTY_NOT_ALLOWED").toUpperCase());
		
			return empService.getElement(address);
		} catch(Exception ex) {
			empService.saveElement(address);
			return empService.getElement(address);
		}
	}
	
	private static Contact createContact() throws Exception {
		Contact contact = new Contact();
		try {
			String string = InputManager.enterString("Landline [xxx-xxxx]","");
			contact.setLandline(RegexUtils.isValidLandline(string) ? string : "");
			InputManager.output((RegexUtils.isValidLandline(string)) ? "":"Not a valid landline\n");

			string = InputManager.enterString("Mobile [xxxx-xxx-xxxx]","");
			contact.setMobile(RegexUtils.isValidMobile(string) ? string : "");
			System.out.print((RegexUtils.isValidMobile(string)) ? "":"Not a valid mobile\n");
			string = "";
			while(!RegexUtils.isValidEmail(string)) {
				string = InputManager.enterString("Email",
					(contact.getLandline().equals("") && contact.getMobile().equals("")) ? "EMPTY_NOT_ALLOWED":"");
				contact.setEmail(RegexUtils.isValidEmail(string) ? string : "");
				System.out.print((RegexUtils.isValidEmail(string)) ? "":"Not a valid email\n");
			}
			return empService.getElement(contact);

		} catch(Exception ex) {
			empService.saveElement(contact);
			return empService.getElement(contact);
		}
	}

	private static Role setRoleToEmployee() throws Exception {
		System.out.println("What role: ");
		empService.getAllElements(Role.class).forEach(System.out::println);
		Role role = empService.getElement(Role.class, Long.valueOf(InputManager.getPositiveNumber("ROLE","EMPTY_NOT_ALLOWED")));
		return role;
	}

	public static Employee addEmployeeRole(Employee employee) throws Exception {
		try {
			Set<Role> roles = employee.getRoles();
			roles.add(setRoleToEmployee());	
			employee.setRoles(roles);
			return employee;
		} catch(Exception ex) {
			throw new Exception("Can't find role");
		}
	}

	public static Employee deleteEmployeeRole(Employee employee) throws Exception {
		Set<Role> roles = employee.getRoles();
		Role role = empService.getElement(Role.class, Long.valueOf(InputManager.getPositiveNumber("ROLE","EMPTY_NOT_ALLOWED")));
		roles.remove(role);	
		employee.setRoles(roles);
		return employee;
	}
	/*
	public static Employee addEmployeeContact(EmployeeService empService, Employee employee, String id) throws Exception {
		System.out.print("\033\143");
		String command = InputManager.enterString("Contact info: [LANDLINE, MOBILE, EMAIL","EMPTY_NOT_ALLOWED");
		Contact contact = empService.getData(employee.getContact().getEmployeeId(), new Contact());
		if(contact == null) {
			contact = new Contact();
		}
		try {
			switch(command.toUpperCase()) {
				case "LANDLINE":
					contact.setLandline(!id.equals("DEL") ? 
						InputManager.enterString("Landline","EMPTY_IS_ALLOWED") : "");
					break;
				case "MOBILE":
					contact.setMobile(!id.equals("DEL") ? 
						InputManager.enterString("Mobile","EMPTY_IS_ALLOWED"):"");
					break;
				case "EMAIL":
					contact.setEmail(!id.equals("DEL") ? 
						InputManager.enterString("Email","EMPTY_IS_ALLOWED"): "");
					break;

			}
			empService.saveElement(contact);
			employee.setContact(contact);
		} catch(Exception ex){
			ex.printStackTrace();
		}
		return employee;
	}*/

	public static void createRole(EmployeeService empService) {
		System.out.print("\033\143");
		empService.getAllElements(Role.class).forEach(System.out::println);
		Role role = new Role();
		role.setRole(InputManager.enterString("NEW ROLE","EMPTY_NOT_ALLOWED").toUpperCase());
		try {
			empService.getElement(role);
			InputManager.output("Role " + role.getRole() + " is existing.");
		} catch(Exception ex) {
			empService.saveElement(role);
			InputManager.output("Role " + role.getRole() + " is created.");
		}
	}

	public static void deleteRole(EmployeeService empService) throws Exception {
		System.out.print("\033\143");
		empService.getAllElements(Role.class).forEach(System.out::println);
		Role role = empService.getElement(Role.class, Long.valueOf(InputManager.getPositiveNumber("ROLE ID","EMPTY_NOT_ALLOWED")));
		//if(empService.isRoleDeletable(role)){
			empService.deleteElement(role);
		//	InputManager.output("Role " + role + " is sucessfully deleted");
		//} else {
		//	InputManager.output("Role " + role + " is cannot be deleted");
		//}
	}

}