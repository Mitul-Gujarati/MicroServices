package com.microservices.organizationservice.dto;

import java.time.LocalDateTime;

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
		description = "OrganizationDto Model Information"
		)
public class OrganizationDto {

	@Schema(
			description = "Organization Id(it will generate automatic)"
			)
	private Long id;
	
	@Schema(
			description = "Organization Name"
			)
	private String organizationName;
	
	@Schema(
			description = "Organization Description"
			)
	private String organizationDescription;
	
	@Schema(
			description = "Organization Code"
			)
	private String organizationCode;
	
	@Schema(
			description = "Organization Created Date(it will generate automatic)"
			)
	private LocalDateTime createdDate;

}
