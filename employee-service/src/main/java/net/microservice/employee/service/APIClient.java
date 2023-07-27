package net.microservice.employee.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import net.microservice.employee.dto.DepartmentDto;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {

	@GetMapping("/api/departments/{departmentCode}")
	DepartmentDto allDepartments(@PathVariable String departmentCode);

}
