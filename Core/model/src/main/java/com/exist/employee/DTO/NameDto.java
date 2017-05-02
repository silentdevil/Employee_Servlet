package com.exist.employee;

public class NameDto {

	private String lastName;
	private String firstName;
	private String middleName;
	private String suffix;
	private String title;

	public String getLastName() {
		return lastName;
	}

	public NameDto setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public NameDto setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getMiddleName() {
		return middleName;
	}

	public NameDto setMiddleName(String middleName) {
		this.middleName = middleName;
		return this;
	}

	public String getSuffix() {
		return suffix;
	}

	public NameDto setSuffix(String suffix) {
		this.suffix = suffix;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public NameDto setTitle(String title) {
		this.title = title;
		return this;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(lastName + "," + firstName + " " + middleName + " " + suffix);
		return sb.toString();
	}

}