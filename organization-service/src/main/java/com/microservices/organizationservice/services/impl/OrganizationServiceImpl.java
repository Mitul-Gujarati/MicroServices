package com.microservices.organizationservice.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.organizationservice.dto.OrganizationDto;
import com.microservices.organizationservice.entity.Organization;
import com.microservices.organizationservice.repository.OrganizationRepository;
import com.microservices.organizationservice.services.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private OrganizationRepository organizationRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public OrganizationDto saveOrganization(OrganizationDto organizationDto) {

		Organization organization = modelMapper.map(organizationDto, Organization.class);
		Organization savedOrganization = organizationRepository.save(organization);

		return modelMapper.map(savedOrganization, OrganizationDto.class);
	}

	@Override
	public List<OrganizationDto> getOrganizations() {
		List<Organization> organizations = organizationRepository.findAll();
		List<OrganizationDto> organizationsDto = organizations.stream()
				.map((org) -> modelMapper.map(org, OrganizationDto.class)).collect(Collectors.toList());
		return organizationsDto;
	}

	@Override
	public OrganizationDto getOrganizationByCode(String organizationCode) {
		Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
		return modelMapper.map(organization, OrganizationDto.class);
	}

}
