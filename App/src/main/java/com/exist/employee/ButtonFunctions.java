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
		return mappedService.getEmployeeDtoById(employeeId);
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
		  EmployeeDto employee = new EmployeeDto();
		  NameDto name = new NameDto();
		      	  name.setLastName(employeeMap.get("lastname").toUpperCase())
		              .setFirstName(employeeMap.get("firstname").toUpperCase())
		              .setMiddleName(employeeMap.get("middlename").toUpperCase())
		              .setTitle(employeeMap.get("title").toUpperCase())
		              .setSuffix(employeeMap.get("suffix").toUpperCase());

	      employee.setEmployeeName(name)        
	      		  .setAddress(
	                new AddressDto().setStreetNo(Integer.parseInt(employeeMap.get("streetno")))
	                                .setStreet(employeeMap.get("street").toUpperCase())
	                                .setBrgy(employeeMap.get("brgy").toUpperCase())
	                                .setCity(employeeMap.get("city").toUpperCase())
	                                .setZipcode(employeeMap.get("zipcode").toUpperCase()))
	              .setGwa(Float.valueOf(employeeMap.get("gwa")))
	              .setBirthday(DatePicker.parseDate(employeeMap.get("birthday")))
	              .setDateHired(DatePicker.parseDate(employeeMap.get("datehired")))
	              .setCurrentlyHired(Boolean.valueOf(employeeMap.get("currentlyhired")))
	              .setContacts(editEmp.createContacts(new ContactDto()
	              		.setContactType(employeeMap.get("employee_ContactType"))
	              		.setContactInfo(employeeMap.get("employee_ContactInfo"))));

	        RoleDto role = mappedService.getRoleDtoById(Long.valueOf(employeeMap.get("employee_Role")));
	        employee = editEmp.addEmployeeRole(employee,role);
	        factoryService.createEmployee(employee);
	    } catch(Exception ex){
	        ex.printStackTrace();
	    }

	}

}

	