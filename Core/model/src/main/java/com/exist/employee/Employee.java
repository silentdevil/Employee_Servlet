package com.exist.employee;
import java.util.Date;
import java.util.Set;
public class Employee {
	private int employeeId;
	private String lastname = "";
	private String firstname = "";
	private String middlename = "";
	private String suffix = "";
	private String title = "";
	private Address address;
	private Date birthday;
	private Float gwa;
	private Date datehired;
	private Boolean currentlyHired;
	private Contact contact;
	private Set<Role> roles;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public Float getGwa() {
		return gwa;
	}
	
	public void setGwa(Float gwa) {
		this.gwa = gwa;
	}
	
	public Date getDatehired() {
		return datehired;
	}
	
	public void setDatehired(Date datehired) {
		this.datehired = datehired;
	}
	
	public Boolean getCurrentlyHired() {
		return currentlyHired;
	}	
	
	public void setCurrentlyHired(Boolean currentlyHired) {
		this.currentlyHired = currentlyHired;
	}
	
	public Contact getContact() {
		return contact;
	}
	
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public Employee addRole(Role role) {
		roles.add(role);
		return this;
	}
	
	public Employee deleteRole(Role role) {
		roles.remove(role);
		return this;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(employeeId + "\t").append(lastname + "," + firstname + " " + middlename + " " + suffix);
		return sb.toString();
	}
}