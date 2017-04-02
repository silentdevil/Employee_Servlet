package com.exist.employee;

public class Contact {
	private long contactId;
	private String landline;
	private String mobile;
	private String email;
	
	/*public Contact(String landline) {
		this.landline = landline;
	}*/
	
	public long getContactId() {
		return contactId;
	}
	
	public void setContactId(long contactId) {
		this.contactId = contactId;
	}
	
	public String getLandline() {
		return landline;
	}
	
	public void setLandline(String landline) {
		this.landline = landline;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString() {
	   StringBuffer sb = new StringBuffer();
		sb.append("Landline:  ").append(landline);
		sb.append(" Mobile: ").append(mobile);
		sb.append(" Email : ").append(email);
		return sb.toString();
	}
}