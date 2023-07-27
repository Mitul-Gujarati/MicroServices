package com.microservices.organizationservice.services;

import java.util.List;

import com.microservices.organizationservice.dto.OrganizationDto;

public interface OrganizationService {

	OrganizationDto saveOrganization(OrganizationDto organizationDto);

	List<OrganizationDto> getOrganizations();
	
	OrganizationDto getOrganizationByCode(String organizationCode);

}
