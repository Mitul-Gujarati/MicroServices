package net.microservice.employee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(
		description = "EmployeeDto Model Information"
		)
public class EmployeeDto {

	@Schema(
			description = "Employee Id(It will generates automatic)"
			)
	private Long id;
	
	@Schema(
			description = "Employee FirstName"
			)
	private String firstName;
	
	@Schema(
			description = "Employee LastName"
			)
	private String lastName;
	
	@Schema(
			description = "Employee Email"
			)
	private String email;
	
	@Schema(
			description = "Department Code Of Employee"
			)
	private String departmentCode;
	
	@Schema(
			description = "Organization Code Of Employee"
			)
	private String organizationCode;

}
