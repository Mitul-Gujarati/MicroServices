package net.microservice.employee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.microservice.employee.dto.APIResponseDto;
import net.microservice.employee.dto.EmployeeDto;
import net.microservice.employee.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
@Tag(
		name = "Employee Service - EmployeeController",
		description = "Employee Controller Exposes REST APIs for Employee-Service"
		)
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	@Operation(
			summary = "Save Employee REST API",
			description = "Save Employee REST API is used to save employee object in a database."
			)
	@ApiResponse(
			responseCode = "201",
			description = "HTTP Status 201 CREATED"
			)
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
		EmployeeDto employee = employeeService.saveEmployee(employeeDto);
		return new ResponseEntity<EmployeeDto>(employee, HttpStatus.CREATED);
	}

	@GetMapping
	@Operation(
			summary = "Get Employee REST API",
			description = "Get Employee REST API is used to Get employee object from the database."
			)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 OK"
			)
	public ResponseEntity<List<EmployeeDto>> allEmployees() {
		List<EmployeeDto> employees = employeeService.getAllEmployees();
		return new ResponseEntity<List<EmployeeDto>>(employees, HttpStatus.OK);
	}

	@GetMapping("/{employeeId}")
	@Operation(
			summary = "Get Employee By Employee-Id REST API",
			description = "Get Employee by Employee-Id REST API is used to Get Employee object from the database using employeeId."
			)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 OK"
			)
	public ResponseEntity<APIResponseDto> allEmployees(@PathVariable Long employeeId) {
		APIResponseDto employee = employeeService.getSingleEmployee(employeeId);
		return new ResponseEntity<APIResponseDto>(employee, HttpStatus.OK);
	}

}
