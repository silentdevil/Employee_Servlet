package com.exist.employee;

import java.util.Set;
import java.util.HashSet;
public class UpdateEmployeeService {
	
	/*public UpdateEmployeeService(Employee employee) {
		this.employee = employee;
	}*/
	
	public static Employee addRole(Employee employee, Role role) {
		Set roles = new HashSet();
		
		roles = employee.getRoles();
		roles.add(role);
		return employee;
	}
}