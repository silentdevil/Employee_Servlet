package com.exist.employee;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

public class EmployeeDto {
	
	private long employeeId;
	private NameDto employeeName;
	private AddressDto address;
	
	private Date birthday;
	private Float gwa;
	
	private Date dateHired;
	private Boolean currentlyHired;
	
	private Set<ContactDto> contacts;

	private Set<RoleDto> roles = new HashSet<>();

	public long getEmployeeId() {
		return employeeId;
	}

	public EmployeeDto setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
		return this;
	}

	public NameDto getEmployeeName(){
		return employeeName;
	}

	public EmployeeDto setEmployeeName(NameDto employeeName){
		this.employeeName = employeeName;
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
	
	public Date getDateHired() {
		return dateHired;
	}
	
	public EmployeeDto setDateHired(Date dateHired) {
		this.dateHired = dateHired;
		return this;
	}
	
	public Boolean getCurrentlyHired() {
		return currentlyHired;
	}	
	
	public EmployeeDto setCurrentlyHired(Boolean currentlyHired) {
		this.currentlyHired = currentlyHired;
		return this;
	}
	
	public Set<ContactDto> getContacts() {
		return contacts;
	}
	
	public EmployeeDto setContacts(Set<ContactDto> contacts) {
		this.contacts = contacts;
		return this;
	}
	
	public Set<RoleDto> getRoles() {
		return roles;
	}
	
	public EmployeeDto setRoles(Set<RoleDto> roles) {
		this.roles = roles;
		return this;
	}
	
	
}
