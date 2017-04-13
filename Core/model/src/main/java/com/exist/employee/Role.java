package com.exist.employee;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;

@Entity
@Table(name = "roles")
public class Role {
	
	
	@Id
	@GeneratedValue
	@Column(name = "roleid")
	private long roleId;
	private String role;
	
	@ManyToMany(mappedBy = "roles")
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