package com.exist.employee;

import java.util.*;

public class EditEmployeeService {
	public EmployeeDto addEmployeeRole(EmployeeDto employee, RoleDto role) {
		try {
			employee.getRoles().add(role);	
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return employee;
	}

	public EmployeeDto delEmployeeRole(EmployeeDto employee, RoleDto role) {
		try {
			employee.getRoles().remove(role);	
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return employee;
	}

	public  Set<ContactDto> createContacts(ContactDto contact) throws Exception {
		Set<ContactDto> contacts = new TreeSet<>();
		if(contact.getContactInfo() != null && !contact.getContactInfo().isEmpty())
			contacts.add(contact);
		return contacts;
	}



	/*public EmployeeDto addEmployeeContact( EmployeeDto employee, ContactDto contact) {
		
		if(contact == null) {
			System.out.println("Contact is null");
			contact = new ContactDto();
		}
		contact.setEmployee(employee);
		employee.setContact(contact);
		return employee;
	}*/

	/*private Set<ContactDto> createContact(EmployeeDto employee) throws Exception {
		Set<ContactDto> contacts = employee.getContacts();
		if(contacts == null)
			contacts = new TreeSet<>();
		//employee.setContacts(contacts);
		ContactDto contact = new ContactDto();
		contact.setEmployee(employee);
		String string = "";
		/*System.out.println("What to do: ");
				for(int i = 1; i <= ContactType.SIZE; i++) {
					System.out.print(ContactType.valueOf(i) + "\t"); 
				}

				System.out.println();
				int choice = InputManager.getPositiveNumber("","");
		switch(ContactType.valueOf(choice)) {
			case EMAIL: {
				string = InputManager.enterString("Email","");
				string = RegexUtils.isValidEmail(string) ? string : "";
				System.out.print((RegexUtils.isValidEmail(string)) ? "":"Not a valid email\n");
				break;
			} case MOBILE: {
				string = InputManager.enterString("Mobile  [XXXX-XXX-XXXX]","");
				string = RegexUtils.isValidMobile(string) ? string : "";
				System.out.print((RegexUtils.isValidMobile(string)) ? "":"Not a valid mobile\n");
				break;
			} case LANDLINE: {
				string = InputManager.enterString("Landline  [XXX-XXXX]","");
				string = RegexUtils.isValidLandline(string) ? string : "";
				System.out.print((RegexUtils.isValidLandline(string)) ? "":"Not a valid mobile\n");
				break;
			} default: {
				return null;
			}
		}
			

			contact.setContactType(ContactType.valueOf(choice).getMessage());
			contact.setContactInfo(string);
			if(contact.getContactInfo() != null && !contact.getContactInfo().isEmpty())
				contacts.add(contact);

			return contacts;
			//return contact.getContactInfo() == "" ? null : contact;
	}

	public EmployeeDto addEmployeeContact(EmployeeDto employee) throws Exception {
		System.out.print("\033\143");
		Set<ContactDto> contacts = createContact(employee);
		
		employee.setContacts(contacts);
		return employee;
	}*/

}