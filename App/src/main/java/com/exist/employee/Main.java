package com.exist.employee;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.Scanner;
//import com.exist.employee.InputManager;

public class Main {
	public static void main(String[] args) {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		EmployeeService empServ = new EmployeeService();
		Scanner scan = new Scanner(System.in);
		HashSet<Role> roles = new HashSet<>();
		/*Role ceo = new Role();
		Role dev = new Role();
		ceo.setRole("ceo");
		ceo.setRole("dev");
		roles.add(ceo);
		roles.add(dev);*/
		/*Role janitor = new Role();
		janitor.setRole("janitor");
		roles.add(janitor);*/
		
		//Address address = empServ.addAddress("Denver","Okc");
		//empServ.addEmployee("Nuggets","Kenver","Lu",empServ.addAddress("Cali","King"),
		//Float.valueOf(4.0f),true, empServ.addContact("6413026"),roles);

		empServ.listEmployees();
		//empServ.listRoles();
		
		while(true) {
			
			//System.out.println("What to do? ADDEMP, DELEMP, EDITEMP");
			String cmd = InputManager.enterString("Action: ADDEMP, DELEMP, EDITEMP", "EMPTY_NOT_ALLOWED");
			try {
				switch(cmd) {
					case "ADD":
						UpdateEmployeeScreen.createEmployee(empServ);
						break;
						
					case "DELEMP":
						empServ.deleteEmployee(InputManager.getPositiveNumber("Employee ID"));
						break;
						
					case "EDITEMP":
						UpdateEmployeeScreen.updateEmployee(empServ);
						break;
				}
			} catch (Exception ex) {
				
			}
			
			empServ.listEmployees();
		
		}
		
		
		
		
		
	}
}