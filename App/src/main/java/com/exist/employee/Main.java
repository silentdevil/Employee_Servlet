package com.exist.employee;

public class Main {
	public static void main(String[] args) {
		EmployeeService empServ = new EmployeeService();
		//Address address = empServ.addAddress("Denver","Okc");
		empServ.addEmployee("Nuggets","Kenver","Lu",empServ.addAddress("Cali","King"),
		Float.valueOf(4.0f),true, empServ.addContact("6413026"));
		
		empServ.listEmployees();
	}
}