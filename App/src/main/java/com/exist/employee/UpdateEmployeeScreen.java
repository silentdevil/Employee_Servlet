package com.exist.employee;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;
public class UpdateEmployeeScreen {
	
	private Employee employee;
	
	public UpdateEmployeeScreen(Employee employee) {
		this.employee = employee;
	}
	
	public static void updateEmployee(EmployeeService empService) {
		
		try {
			//Employee employee = empService.searchEmployee(InputManager.getPositiveNumber("Employee ID"));
			Employee employee = showEmployeeDetails(empService);
			OUTER:
			while(true) {
				
				//System.out.println("What to do? ADDEMP, DELEMP, EDITEMP");
				String cmd = InputManager.enterString("Action: ADDROLE, DELROLE, ADDCONTACT, DELCONTACT, UPDATECONTACT, BACK", "EMPTY_NOT_ALLOWED");
				try {
					switch(cmd) {
						case "ADDROLE":
							
							break;
						case "BACK":
							break OUTER;
					}
				} catch (Exception ex) {
					
				}
				empService.updateEmployee(employee);
				empService.listEmployees();
			
			}
		} catch(Exception ex) {}	
	}
	
	public static Employee showEmployeeDetails(EmployeeService empService) {
		try {
			Employee employee = empService.searchEmployee(InputManager.getPositiveNumber("Employee ID"));
			
			System.out.printf("Name: %s, %s %s %s\n", employee.getLastname(), employee.getFirstname(), 
				employee.getMiddlename(), employee.getSuffix());
			
			System.out.println("Address: " + employee.getAddress());
			System.out.println("Birthday: " + employee.getBirthday());
			System.out.println("GWA: " + employee.getGwa());
			System.out.println("Date hired: " + employee.getDatehired());
			System.out.println("Currently hired: " + employee.getCurrentlyHired());
			System.out.println("Contacts: " + employee.getContact());
			System.out.println("Roles: " + empService.listEmployeeRoles(employee));
			
			return employee;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static void createEmployee(EmployeeService empService) {
		Employee employee = new Employee();
		try {
		employee.setLastname(InputManager.enterString("Lastname", "EMPTY_NOT_ALLOWED"));
		employee.setFirstname(InputManager.enterString("Firstname", "EMPTY_NOT_ALLOWED"));
		employee.setMiddlename(InputManager.enterString("Middlename", "EMPTY_NOT_ALLOWED"));
		employee.setSuffix(InputManager.enterString("Suffix",""));
		employee.setTitle(InputManager.enterString("Title",""));
		//employee.setAddress(createAddress(empService));
		//employee.setBirthday(new Date(InputManager.getPositiveNumber("MONTH"),
		//		InputManager.getPositiveNumber("DAY"), InputManager.getPositiveNumber("YEAR")));
		//employee.setGwa(InputManager.getPositiveFloat("GWA"));
		//employee.setDatehired(new Date(InputManager.getPositiveNumber("MONTH"),
		//		InputManager.getPositiveNumber("DAY"), InputManager.getPositiveNumber("YEAR")));
		//employee.setCurrentlyHired(InputManager.getBoolean("CURRENTLY HIRED"));
		//employee.setContact(createContact(empService));
		employee.setRoles(new HashSet<Role>());
		employee.getRoles().add(setRoleToEmployee(empService));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		empService.addEmployee(employee);
		empService.listEmployees();
		
		
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
		} catch(Exception ex) {}
		
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
	
	
	
}