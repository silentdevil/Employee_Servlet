package com.exist.employee;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.stream.Collectors;

public class ButtonFunctions {


	private FactoryService factoryService;
	private EditEmployeeService editEmp;
	private MappedService mappedService;
	private EmployeeService empService;
	// = factoryService.getEmployeeService();
	//private DtoMapper mapper;// = factoryService.getMapper();
	public ButtonFunctions(FactoryService factoryService, EditEmployeeService editEmp) {
		this.editEmp = editEmp;
		this.factoryService = factoryService;
		mappedService = factoryService.getMappedService();
		empService = mappedService.getEmployeeService();
	}

	public FactoryService getFactoryService() {
		return factoryService;
	}

	private EmployeeDto employee;
	private List<Employee> empList;
	private List<Role> roleList;

	
	public EmployeeDto editEmployee(long employeeId) {
		employee = mappedService.getEmployeeDtoById(employeeId);
		return employee;
	}

	public List<Object[]> sort(String id) {
		return empService.getAllEmployees(id);
	}

	public List<Object[]> deleteEmployee(long employeeId) {
		empService.deleteElement(empService.findEmployeeById(employeeId));
		return empService.getAllEmployees("dateHired");
	}

	public List<RoleDto> getAllRoles() {
		return mappedService.getAllRoles();
	}

	public void saveEmployee(Map<String,String> employeeMap) {
		try {
		  employee = new EmployeeDto();

		  employee = employeeFillValues(employeeMap, employee);
		 
	        employee.setContacts(editEmp.createContacts(new ContactDto()
	              		.setContactType(employeeMap.get("employee_ContactType"))
	              		.setContactInfo(employeeMap.get("employee_ContactInfo"))));

	        RoleDto role = mappedService.getRoleDtoById(Long.valueOf(employeeMap.get("employee_Role")));
	        employee = editEmp.addEmployeeRole(employee,role);
	        factoryService.createEmployee(employee);
	    } catch(Exception ex){
	        ex.printStackTrace();
	    }

	}

	public EmployeeDto employeeFillValues(Map<String, String> employeeMap, EmployeeDto employee) {
		 NameDto name = new NameDto();
		      	  name.setLastName(employeeMap.get("txt_LastName").toUpperCase())
		              .setFirstName(employeeMap.get("txt_FirstName").toUpperCase())
		              .setMiddleName(employeeMap.get("txt_MiddleName").toUpperCase())
		              .setTitle(employeeMap.get("txt_Title").toUpperCase())
		              .setSuffix(employeeMap.get("txt_Suffix").toUpperCase());

	    return employee.setEmployeeName(name)        
	      		  .setAddress(
	                new AddressDto().setStreetNo(Integer.parseInt(employeeMap.get("txt_StreetNo")))
	                                .setStreet(employeeMap.get("txt_Street").toUpperCase())
	                                .setBrgy(employeeMap.get("txt_Brgy").toUpperCase())
	                                .setCity(employeeMap.get("txt_City").toUpperCase())
	                                .setZipcode(employeeMap.get("txt_Zipcode").toUpperCase()))
	              .setGwa(Float.valueOf(employeeMap.get("txt_Gwa")))
	              .setBirthday(DatePicker.parseDate(employeeMap.get("txt_Birthday")))
	              .setDateHired(DatePicker.parseDate(employeeMap.get("txt_DateHired")))
	              .setCurrentlyHired(Boolean.valueOf(employeeMap.get("txt_CurrentlyHired")));
	}

	public void addNewRole(List<RoleDto> roleList, String role) {

		RoleDto r = new RoleDto();
		r.setRole(role);

		if(!roleList.contains(r)) {
			empService.saveElement(factoryService.createRole(r));
		}
	}

	public void editRole(long id, String name) {
		RoleDto role = mappedService.getRoleDtoById(id);
		role.setRole(name.toUpperCase());
		if(!name.isEmpty())
			empService.updateElement(factoryService.createRole(role));
	}

	public void deleteRole(long id) {
		RoleDto role = mappedService.getRoleDtoById(id);
		empService.deleteElement(factoryService.createRole(role));
	}

	public EmployeeDto addEmployeeRole(long roleId) {
		RoleDto role = mappedService.getRoleDtoById(roleId);
		employee = editEmp.addEmployeeRole(employee, role);
		return employee;
	}

	public EmployeeDto deleteEmployeeRole(long roleId) {
		//employee = mappedService.getEmployeeDtoById(employeeId);
		RoleDto role = mappedService.getRoleDtoById(roleId);
		employee = editEmp.deleteEmployeeRole(employee, role);
		return employee;
	}

	public void updateEmployee(Map<String, String> employeeMap) {
		employee = employeeFillValues(employeeMap, employee);
		factoryService.createEmployee(employee);
	}

	public EmployeeDto addEmployeeContact(ContactDto contact) {
		//employee = mappedService.getEmployeeDtoById(employeeId);
		employee = editEmp.addEmployeeContact(employee, contact);
		return employee;
	}

	public EmployeeDto deleteEmployeeContact(long contactId) {
		ContactDto contact = mappedService.getContactDtoById(contactId);
		employee = editEmp.deleteEmployeeContact(employee, contact);
		System.out.println(employee.getContacts());
		return employee;
	}

	public RoleDto outputEmployees(long roleId) {
		return mappedService.getRoleDtoById(roleId);
	}

	public EmployeeDto regEmpAddContact(ContactDto contact) {
		employee = new EmployeeDto();
		employee.getContacts().add(contact);
		return employee;
	}

}

	