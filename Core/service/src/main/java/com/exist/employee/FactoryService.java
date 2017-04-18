package com.exist.employee;
import java.util.*;
public class FactoryService {

	private static EmployeeService empService = new EmployeeService();

	
	public Employee createEmployee(EmployeeDto employeeDto) {
		Employee employee = new Employee();
		try {
			employee.setEmployeeId(employeeDto.getEmployeeId());
			employee.setLastname(employeeDto.getLastname());
			employee.setFirstname(employeeDto.getFirstname());
			employee.setMiddlename(employeeDto.getMiddlename());
			employee.setSuffix(employeeDto.getSuffix());
			employee.setTitle(employeeDto.getTitle());
			employee.setAddress(createAddress(employeeDto.getAddress()));
			employee.setBirthday(employeeDto.getBirthday());
			employee.setGwa(employeeDto.getGwa());
			employee.setDatehired(employeeDto.getDatehired());
			employee.setCurrentlyHired(employeeDto.getCurrentlyHired());
			Contact contact = createContact(employeeDto.getContact());
			contact.setEmployeeId(employeeDto.getEmployeeId());
			contact.setEmployee(employee);
			employee.setContact(contact);
			employee.setRoles(createRoleSet(employeeDto.getRoles()));
		
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return employee;
	}
	
	public void updateDto(Object object) {
		empService.updateElement(object);
	}
	
	public void saveDto(Object object){
		empService.saveElement(object);	
	}
	
	public  Address createAddress(AddressDto addressDto) throws Exception {
		Address address = new Address();
		try {
			address.setStreetno(addressDto.getStreetno());
			address.setStreet(addressDto.getStreet());
			address.setBrgy(addressDto.getBrgy());
			address.setCity(addressDto.getCity());
			address.setZipcode(addressDto.getZipcode());
		
			return empService.getElement(address);
		} catch(Exception ex) {
			empService.saveElement(address);
			return empService.getElement(address);
		}
	}
	
	public  Contact createContact(ContactDto contactDto) throws Exception {
			Contact contact = new Contact();
			
			contact.setLandline(contactDto.getLandline());
			contact.setMobile(contactDto.getMobile());
			contact.setEmail(contactDto.getEmail());
			return contact;
		
	}
	
	public Role createRole(RoleDto roleDto) {
		Role role = new Role();
		try {
			role.setRole(roleDto.getRole());
			role.setRoleId(roleDto.getRoleId());
		} finally {
			return role;
		}
	}
	
	public Set<Role> createRoleSet(Set<RoleDto> roleDtoSet) {
		List<Role> roles = new ArrayList<>();
		roleDtoSet.forEach(r -> roles.add(createRole(r)));
		return new HashSet<Role>(roles);
	}
	
	
}