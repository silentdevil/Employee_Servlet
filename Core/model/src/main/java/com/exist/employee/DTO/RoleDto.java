package com.exist.employee;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import java.util.Set;

public class RoleDto {
	
	
	private long roleId;
	private String role;
	
	private Set<EmployeeDto> employees = new HashSet<>();
	

	public long getRoleId() {
		return roleId;
	}
	
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

	public Set<EmployeeDto> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<EmployeeDto> employees) {
		this.employees = employees;
	}
	
	public boolean equals(Object obj) {
      if (obj == null) return false;
      if (!this.getClass().equals(obj.getClass())) return false;

      RoleDto obj2 = (RoleDto)obj;
      return this.role.equals(obj2.getRole());
   }
   public int hashCode() {
      int tmp = 0;
      tmp = (role).hashCode();
      return tmp;
   }
   
   public String toString() {
	   return roleId + " - " + role;
   }
}