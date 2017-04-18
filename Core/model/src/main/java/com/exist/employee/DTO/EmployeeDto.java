package com.exist.employee;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;


public class EmployeeDto {
	
	private long employeeId;
	private String lastname = "";
	private String firstname = "";
	private String middlename = "";
	private String suffix = "";
	private String title = "";
	private AddressDto address;
	
	private Date birthday;
	private Float gwa;
	
	private Date datehired;
	private Boolean currentlyHired;
	
	private ContactDto contact;

	private Set<RoleDto> roles = new HashSet<>();

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getLastname() {
		return lastname;
	}

	public EmployeeDto setLastname(String lastname) {
		this.lastname = lastname;
		return this;
	}

	public String getFirstname() {
		return firstname;
	}

	public EmployeeDto setFirstname(String firstname) {
		this.firstname = firstname;
		return this;
	}

	public String getMiddlename() {
		return middlename;
	}

	public EmployeeDto setMiddlename(String middlename) {
		this.middlename = middlename;
		return this;
	}

	public String getSuffix() {
		return suffix;
	}

	public EmployeeDto setSuffix(String suffix) {
		this.suffix = suffix;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public EmployeeDto setTitle(String title) {
		this.title = title;
		return this;
	}

	public AddressDto getAddress() {
		return address;
	}

	public EmployeeDto setAddress(AddressDto address) {
		this.address = address;
		return this;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public EmployeeDto setBirthday(Date birthday) {
		this.birthday = birthday;
		return this;
	}
	
	public Float getGwa() {
		return gwa;
	}
	
	public EmployeeDto setGwa(Float gwa) {
		this.gwa = gwa;
		return this;
	}
	
	public Date getDatehired() {
		return datehired;
	}
	
	public EmployeeDto setDatehired(Date datehired) {
		this.datehired = datehired;
		return this;
	}
	
	public Boolean getCurrentlyHired() {
		return currentlyHired;
	}	
	
	public EmployeeDto setCurrentlyHired(Boolean currentlyHired) {
		this.currentlyHired = currentlyHired;
		return this;
	}
	
	public ContactDto getContact() {
		return contact;
	}
	
	public EmployeeDto setContact(ContactDto contact) {
		this.contact = contact;
		return this;
	}
	

	public Set<RoleDto> getRoles() {
		return roles;
	}
	
	public EmployeeDto setRoles(Set<RoleDto> roles) {
		this.roles = roles;
		return this;
	}
	
	

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(employeeId + "\t").append(lastname + "," + firstname + " " + middlename + " " + suffix);
		return sb.toString();
	}
}
