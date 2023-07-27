package net.microservice.employee.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import io.github.resilience4j.retry.annotation.Retry;
import net.microservice.employee.dto.APIResponseDto;
import net.microservice.employee.dto.DepartmentDto;
import net.microservice.employee.dto.EmployeeDto;
import net.microservice.employee.dto.OrganizationDto;
import net.microservice.employee.entity.Employee;
import net.microservice.employee.repositories.EmployeeRepository;
import net.microservice.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ModelMapper modelMapper;

//	@Autowired
//	private RestTemplate restTemplate;

	@Autowired
	private WebClient webClient;

//	@Autowired
//	private APIClient apiClient;

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		Employee employee = modelMapper.map(employeeDto, Employee.class);
		Employee savedEmployee = employeeRepository.save(employee);
		return modelMapper.map(savedEmployee, EmployeeDto.class);
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		List<EmployeeDto> employeesDto = employees.stream().map(emp -> modelMapper.map(emp, EmployeeDto.class))
				.collect(Collectors.toList());
		return employeesDto;
	}

	// @CircuitBreaker(name = "${spring.application.name}", fallbackMethod =
	// "getDefaultDepartment")
	@Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
	@Override
	public APIResponseDto getSingleEmployee(Long employeeId) {

		LOGGER.info("inside getEmployeeById() method");

		Employee employee = employeeRepository.findById(employeeId).get();

		/*
		 * when we use RestTemplet that time we can use this way to API call for other
		 * service. ResponseEntity<DepartmentDto> response =
		 * restTemplate.getForEntity("http://localhost:8080/api/departments/" +
		 * employee.getDepartmentCode(), DepartmentDto.class); DepartmentDto
		 * departmentDto = response.getBody();
		 */
		// when we use WebClient that time we can use this way to API call for other
		// service.
		DepartmentDto departmentDto = webClient.get()
				.uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode()).retrieve()
				.bodyToMono(DepartmentDto.class).block();

		OrganizationDto organizationDto = webClient.get()
				.uri("http://localhost:8083/api/organizations/" + employee.getOrganizationCode()).retrieve()
				.bodyToMono(OrganizationDto.class).block();

		// when we use OpenFeign that time we can use this way to API call for other
		// service.
//		DepartmentDto departmentDto = apiClient.allDepartments(employee.getDepartmentCode());

		APIResponseDto apiResponseDto = new APIResponseDto();
		apiResponseDto.setEmployeeDto(modelMapper.map(employee, EmployeeDto.class));
		apiResponseDto.setDepartmentDto(departmentDto);
		apiResponseDto.setOrganizationDto(organizationDto);
		return apiResponseDto;
	}

	// It is a fallbackMethod, when we implement fallbackMethod we have to set
	// return type and method parameter same.
	public APIResponseDto getDefaultDepartment(Long employeeId, Exception exception) {

		LOGGER.info("inside getDefaultDepartment() method");

		Employee employee = employeeRepository.findById(employeeId).get();

		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setDepartmentName("R&D Department");
		departmentDto.setDepartmentCode("RD001");
		departmentDto.setDepartmentDescription("Research and Development Deaprtment");

		OrganizationDto organizationDto = new OrganizationDto();
		organizationDto.setOrganizationName("Dummy");
		organizationDto.setOrganizationDescription("Default Organization.");
		organizationDto.setOrganizationCode("AMC007");

		APIResponseDto apiResponseDto = new APIResponseDto();
		apiResponseDto.setEmployeeDto(modelMapper.map(employee, EmployeeDto.class));
		apiResponseDto.setDepartmentDto(departmentDto);
		apiResponseDto.setOrganizationDto(organizationDto);
		return apiResponseDto;

	}

}
