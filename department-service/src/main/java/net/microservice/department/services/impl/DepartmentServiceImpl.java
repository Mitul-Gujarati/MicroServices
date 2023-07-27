package net.microservice.department.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.microservice.department.dto.DepartmentDto;
import net.microservice.department.entity.Department;
import net.microservice.department.repository.DepartmentRepository;
import net.microservice.department.services.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

		// convert DepartmentDto to Department
		Department department = modelMapper.map(departmentDto, Department.class);

		// save department into database.
		Department savedDepartment = departmentRepository.save(department);

		// convert Department into DepartmentDto and return it.
		return modelMapper.map(savedDepartment, DepartmentDto.class);
	}

	@Override
	public List<DepartmentDto> getAllDepartemtns() {
		List<Department> allDepartments = departmentRepository.findAll();
		List<DepartmentDto> allDepartmentsDto = allDepartments.stream()
				.map(department -> modelMapper.map(department, DepartmentDto.class)).collect(Collectors.toList());
		return allDepartmentsDto;
	}

	@Override
	public DepartmentDto getDepartmentByCode(String departmentCode) {
		Department department = departmentRepository.findByDepartmentCode(departmentCode);
		return modelMapper.map(department, DepartmentDto.class);
	}

}
