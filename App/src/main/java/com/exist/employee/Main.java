package com.exist.employee;

import java.util.logging.Level;
import java.util.function.Consumer;
import java.util.List; 
public class Main {
	public static void main(String[] args) throws Exception {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		EmployeeService empServ = new EmployeeService();
		String order = "";
		List<Employee> list = empServ.getAllElements(Employee.class);
		Consumer<Employee> consumer = System.out::println;
		OUTER:
		while(true) {
			System.out.print("\033\143\n\n");
			list.forEach(consumer);
			String cmd = InputManager.enterString("Action: ADDEMP, DELEMP, EDITEMP, MODIFYROLES\n SORT_GWA, SORT_HIREDATE, SORT_LASTNAME",
			 "EMPTY_NOT_ALLOWED");
			try {
				switch(cmd.toUpperCase()) {
					case "ADDEMP":
						FactoryService.createEmployee();
						break;
						
					case "DELEMP":
						empServ.deleteElement(empServ.getElement(Employee.class,Long.valueOf(InputManager.getPositiveNumber("Employee ID","EMPTY_NOT_ALLOWED"))));
						break;
						
					case "EDITEMP":
						UpdateEmployeeScreen.updateEmployee(empServ);
						break;
					case "MODIFYROLES":
						UpdateEmployeeScreen.roleScreen(empServ);
						break;
					case "SORT_GWA":
						list = empServ.getAllElements(Employee.class).stream().sorted((e1,e2) -> Float.compare(e1.getGwa(), e2.getGwa()))
									  .collect(java.util.stream.Collectors.toList());
						consumer = emp -> System.out.printf("%d\t%s, %s %s %.2f\n",emp.getEmployeeId(),emp.getLastname(),emp.getFirstname(),emp.getMiddlename(),emp.getGwa());
						break;
					case "SORT_HIREDATE":
						list = empServ.getAllElements(Employee.class, "FROM com.exist.employee.Employee e ORDER BY e.datehired");
						consumer = emp -> System.out.printf("%d\t%s, %s %s %s\n",emp.getEmployeeId(),emp.getLastname(),emp.getFirstname(),emp.getMiddlename(),emp.getDatehired());
						break;
					case "SORT_LASTNAME":
						list = empServ.getAllElements(Employee.class, "FROM com.exist.employee.Employee e ORDER BY e.lastname");
						consumer = emp -> System.out.printf("%d\t%s, %s %s\n",emp.getEmployeeId(),emp.getLastname(),emp.getFirstname(),emp.getMiddlename());
						break;
					case "EXIT":
						break OUTER;
					default:
						consumer = System.out::println;
				}
			} catch (Exception ex) {
				InputManager.output(ex.toString());
			}
		}
	}
}