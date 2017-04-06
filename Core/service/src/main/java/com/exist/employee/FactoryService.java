package com.exist.employee;
import java.util.*;
public class FactoryService {

	
	
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
		employee.setGwa(InputManager.getPositiveFloat("GWA","EMPTY_NOT_ALLOWED"));
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
	
	private static Address createAddress(EmployeeService empService) throws Exception {
		System.out.println("Enter ADDRESS!");
		Address address = new Address();
		try {
			address.setStreetno(InputManager.getPositiveNumber("Street no","EMPTY_NOT_ALLOWED"));
			address.setStreet(InputManager.enterString("Street","EMPTY_NOT_ALLOWED").toUpperCase());
			address.setBrgy(InputManager.enterString("Brgy", "EMPTY_NOT_ALLOWED").toUpperCase());
			address.setCity(InputManager.enterString("City", "EMPTY_NOT_ALLOWED").toUpperCase());
			address.setZipcode(InputManager.enterString("Zipcode","EMPTY_NOT_ALLOWED").toUpperCase());
		} catch(Exception ex) {
			ex.printStackTrace();
			
		}
		if(empService.getData(address) == null)
			empService.saveElement(address);
			
		return empService.getData(address);
	}
	
	private static Contact createContact(EmployeeService empService) throws Exception {
		Contact contact = new Contact();
		try {
			contact.setLandline(InputManager.enterString("Landline",""));
			contact.setMobile(InputManager.enterString("Mobile",""));
			contact.setEmail(InputManager.enterString("Email",
				(contact.getLandline().equals("") && contact.getMobile().equals("")) ? "EMPTY_NOT_ALLOWED":""));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		if(empService.getData(contact) == null)
			empService.saveElement(contact);
		return empService.getData(contact);
	}

	private static Role setRoleToEmployee(EmployeeService empService) throws Exception {
		System.out.println("What role: ");
		empService.listRoles();
		try {
			Role role = empService.getData(Long.valueOf(InputManager.getPositiveNumber("ROLE","EMPTY_NOT_ALLOWED")),new Role());
			return role;
		} catch(Exception ex){
			//ex.printStackTrace();
				return null;
		}
	
	}

	public static Employee addEmployeeRole(EmployeeService empService, Employee employee) throws Exception {
		Set<Role> roles = empService.listEmployeeRoles(employee);
		roles.add(setRoleToEmployee(empService));	
		employee.setRoles(roles);
		return employee;
	}

	public static Employee deleteEmployeeRole(EmployeeService empService, Employee employee) throws Exception {
		Set<Role> roles = empService.listEmployeeRoles(employee);
		Role role = empService.getData(Long.valueOf(InputManager.getPositiveNumber("ROLE","EMPTY_NOT_ALLOWED")),new Role());
		roles.remove(role);	
		employee.setRoles(roles);
		return employee;
	}

	public static Employee addEmployeeContact(EmployeeService empService, Employee employee, String id) throws Exception {
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
		Role role = empService.getData(Long.valueOf(InputManager.getPositiveNumber("ROLE ID","EMPTY_NOT_ALLOWED")), new Role());
		if(empService.isRoleDeletable(role))
			empService.deleteElement(role);
		else {
			System.out.println("Role " + role + " is sucessfully deleted");
		}
		
	}

}