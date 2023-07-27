package net.microservice.department.services;

import java.util.List;

import net.microservice.department.dto.DepartmentDto;

public interface DepartmentService {
	
	DepartmentDto saveDepartment(DepartmentDto departmentDto);	
	List<DepartmentDto> getAllDepartemtns();
	DepartmentDto getDepartmentByCode(String departmentCode);
	
}
