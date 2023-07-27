package net.microservice.employee.service;

import java.util.List;

import net.microservice.employee.dto.APIResponseDto;
import net.microservice.employee.dto.EmployeeDto;

public interface EmployeeService {
	
	EmployeeDto saveEmployee(EmployeeDto employeeDto);
	
	List<EmployeeDto> getAllEmployees();
	
	APIResponseDto getSingleEmployee(Long employeeId);

}
