package com.exist.employee;

import java.util.*;

public class MappedService {
	private DtoMapper mapper;
	private EmployeeService empService;

	public MappedService(EmployeeService empService,DtoMapper mapper) {
		this.mapper = mapper;
		this.empService = empService;
	}

	public EmployeeService getEmployeeService() {
		return empService;
	}

	public DtoMapper getMapper() {
		return mapper;
	}

	public EmployeeDto getEmployeeDtoById(long id) {
		Employee emp = empService.findEmployeeById(id);
		return mapper.mapEmployeeDto(emp);
	}

	public RoleDto getRoleDtoById(long id) {
		Role role = empService.getRoleById(id);
		return mapper.mapRoleDto(role);
	}


	public List<RoleDto> getAllRoles() {
		List<RoleDto> roleList = new ArrayList<>();
		empService.getAllRoles().forEach(r -> {
			roleList.add(mapper.mapRoleDto(r));
		});
		
		return roleList;
	}

	public ContactDto getContactDtoById(long id) {
		Contact contact = empService.getContactById(id);
		return mapper.mapContactSingle(contact);
	}
}