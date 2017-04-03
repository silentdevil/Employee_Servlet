package com.exist.employee;

public class Role {
	
	private long roleId;
	private String role;
	
	
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
	
	public boolean equals(Object obj) {
      if (obj == null) return false;
      if (!this.getClass().equals(obj.getClass())) return false;

      Role obj2 = (Role)obj;
      if((this.roleId == obj2.getRoleId()) && (this.role.equals(obj2.getRole())))
      {
         return true;
      }
      return false;
   }
   public int hashCode() {
      int tmp = 0;
      tmp = (roleId + role).hashCode();
      return tmp;
   }
   
   public String toString() {
	   return roleId + "\t" + role;
   }
}