package com.exist.employee;

public class ContactDto implements Comparable<ContactDto>{
	
	private long contactId;
	private long employeeId;
	
	private EmployeeDto employee;
	private String contactType;
	private String contactInfo;


	public long getContactId() {
		return contactId;
	}

	public ContactDto setContactId(long contactId) {
		this.contactId = contactId;
		return this;
	}
	
	public long getEmployeeId() {
		return employeeId;
	}
	
	public ContactDto setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
		return this;
	}

	public EmployeeDto getEmployee() {
		return employee;
	}

	public ContactDto setEmployee(EmployeeDto employee) {
		this.employee = employee;
		return this;
	}

	public String getContactType(){
		return contactType;
	}

	public ContactDto setContactType(String contactType){
		this.contactType = contactType;
		return this;
	}

	public String getContactInfo(){
		return contactInfo;
	}

	public ContactDto setContactInfo(String contactInfo){
		this.contactInfo = contactInfo;
		return this;
	}
	
	
	public String toString() {
	   return contactType + " : " + contactInfo;
	}

	public int compareTo(ContactDto cto) {
		return (contactType+contactInfo).compareTo((cto.getContactType()+cto.getContactInfo()));
	}

	@Override
   	public boolean equals(Object obj) {
       if(obj == null || getClass() != obj.getClass())
         return false;

        ContactDto add2 = (ContactDto) obj;

         return this.contactType.equals(add2.getContactType()) && 
         		this.contactInfo.equals(add2.getContactInfo());
        
   }

   @Override
   public int hashCode() {
        return java.util.Objects.hash(contactType,contactInfo);
    }

}