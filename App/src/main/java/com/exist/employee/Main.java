package com.exist.employee;

public class Main {
	public static void main(String[] args) {
		EmployeeService empServ = new EmployeeService();
		//Address address = empServ.addAddress("Denver","Okc");
		empServ.addEmployee("Nuggets","Kenver","Lu");
		empServ.addAddress("Cali","King");
		empServ.listEmployees();
	}
}