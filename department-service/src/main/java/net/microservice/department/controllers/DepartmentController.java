package net.microservice.department.controllers;

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
import net.microservice.department.dto.DepartmentDto;
import net.microservice.department.services.DepartmentService;

@RestController
@RequestMapping("/api/departments")
@Tag(
		name = "Department Service - DepartmentController",
		description = "Department Controller Exposes REST APIs for Department-Service"
		)
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@Operation(
			summary = "Save Department REST API",
			description = "Save Department REST API is used to save department object in a database."
			)
	@ApiResponse(
			responseCode = "201",
			description = "HTTP Status 201 CREATED"
			)
	@PostMapping
	public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto) {
		DepartmentDto saveDepartment = departmentService.saveDepartment(departmentDto);
		return new ResponseEntity<DepartmentDto>(saveDepartment, HttpStatus.CREATED);
	}

	@Operation(
			summary = "Get Department REST API",
			description = "Get Department REST API is used to Get department object from the database."
			)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 OK"
			)
	@GetMapping
	public ResponseEntity<List<DepartmentDto>> allDepartments() {
		List<DepartmentDto> departemtns = departmentService.getAllDepartemtns();
		return new ResponseEntity<List<DepartmentDto>>(departemtns, HttpStatus.OK);
	}

	@Operation(
			summary = "Get Department By Department-Code REST API",
			description = "Get Department by department-code REST API is used to Get department object from the database using department-code ."
			)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 OK"
			)
	@GetMapping("/{departmentCode}")
	public ResponseEntity<DepartmentDto> allDepartments(@PathVariable String departmentCode) {
		DepartmentDto department = departmentService.getDepartmentByCode(departmentCode);
		return new ResponseEntity<DepartmentDto>(department, HttpStatus.OK);
	}
}
