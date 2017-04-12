package com.exist.employee;
import java.util.Set;
import java.util.HashSet;
public class Role {
	
	private long roleId;
	private String role;
	private Set<Employee> employees = new HashSet<>();
	
	public Role() {
	}
	
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

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	
	public boolean equals(Object obj) {
      if (obj == null) return false;
      if (!this.getClass().equals(obj.getClass())) return false;

      Role obj2 = (Role)obj;
      return this.role.equals(obj2.getRole());
   }
   public int hashCode() {
      int tmp = 0;
      tmp = (roleId + role).hashCode();
      return tmp;
   }
   
   public String toString() {
	   return roleId + " - " + role;
   }
}