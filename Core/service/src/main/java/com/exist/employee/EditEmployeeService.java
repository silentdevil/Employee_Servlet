package com.exist.employee;

import java.util.Set;

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

	public EmployeeDto addEmployeeContact( EmployeeDto employee, ContactDto contact) {
		
		if(contact == null) {
			System.out.println("Contact is null");
			contact = new ContactDto();
		}
		contact.setEmployee(employee);
		employee.setContact(contact);
		return employee;
	}

}
