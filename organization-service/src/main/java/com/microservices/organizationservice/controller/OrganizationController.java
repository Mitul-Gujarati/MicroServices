package com.microservices.organizationservice.controller;

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

import com.microservices.organizationservice.dto.OrganizationDto;
import com.microservices.organizationservice.services.OrganizationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/organizations")
@Tag(
		name = "Organization Service - OrganizationController",
		description = "Organization Controller Exposes REST APIs for Organization-Service"
		)
public class OrganizationController {

	@Autowired
	private OrganizationService organizationService;

	@PostMapping
	@Operation(
			summary = "Save Organization REST API",
			description = "Save Organization REST API is used to save organization object in a database."
			)
	@ApiResponse(
			responseCode = "201",
			description = "HTTP Status 201 CREATED"
			)
	public ResponseEntity<OrganizationDto> createOrganization(@RequestBody OrganizationDto organizationDto) {
		OrganizationDto organization = organizationService.saveOrganization(organizationDto);
		return new ResponseEntity<OrganizationDto>(organization, HttpStatus.CREATED);
	}

	@Operation(
			summary = "Get Organization REST API",
			description = "Get Organization REST API is used to Get organization object from the database."
			)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 OK"
			)
	@GetMapping
	public ResponseEntity<List<OrganizationDto>> getAllOrganizations() {
		List<OrganizationDto> organizations = organizationService.getOrganizations();
		return new ResponseEntity<List<OrganizationDto>>(organizations, HttpStatus.OK);
	}

	@GetMapping("/{organizationCode}")
	@Operation(
			summary = "Get Organization By Organization-Code REST API",
			description = "Get Organization by Organization-code REST API is used to Get organization object from the database using organization-code ."
			)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 OK"
			)
	public ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable String organizationCode) {
		OrganizationDto organization = organizationService.getOrganizationByCode(organizationCode);
		return new ResponseEntity<OrganizationDto>(organization, HttpStatus.OK);
	}

}
