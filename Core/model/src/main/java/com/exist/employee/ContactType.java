package com.exist.employee;

public enum ContactType {
	EMAIL(1,"EMAIL"),
	MOBILE(2,"MOBILE"),
	LANDLINE(3,"LANDLINE");

	private int id;
	private String message;
	public static int SIZE = ContactType.values().length;

	ContactType(int id, String message) {
		this.id = id;
		this.message = message;
	}

	public String toString() {
	  return id + ": " + message; 
	}

	public String getMessage() {
		return message;
	}



	public static ContactType valueOf(int i) {
		for(ContactType t : ContactType.values()) {

			if(t.id == i)
				return t;
			}
		return null;

	}


}