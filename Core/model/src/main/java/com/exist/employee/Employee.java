package com.exist.employee;
import java.util.Date;

public class Employee {
	private int employeeId;
	private String lastname;
	private String firstname;
	private String middlename;
	private String suffix;
	private String title;
	private Address address;
	private Date birthday;
	private Float gwa;
	private Boolean currentlyHired;
	private Contact contact;

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
	
	

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Lastname : ").append(lastname);
		sb.append(" ,Firstname : ").append(firstname);
		sb.append(" , Middlename : ").append(middlename);
		sb.append(" , Address: ").append(address);
		return sb.toString();
	}
}