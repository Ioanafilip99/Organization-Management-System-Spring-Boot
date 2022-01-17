package com.example.project.mapper;

import com.example.project.dto.OrganizationRequest;
import com.example.project.model.Organization;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {
    public Organization organizationRequestToOrganization(OrganizationRequest organizationRequest){
        return new Organization(organizationRequest.getOrganizationName());
    }
}
