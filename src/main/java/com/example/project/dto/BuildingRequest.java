package com.example.project.dto;

import com.example.project.model.Organization;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BuildingRequest {
    @NotNull(message = "Building address can't be null!")
    @NotEmpty(message = "Building address can't be empty!")
    private String buildingAddress;

    private Organization organization;

    public BuildingRequest(String buildingAddress, Organization organization) {
        this.buildingAddress = buildingAddress;
        this.organization = organization;
    }

    public BuildingRequest() {
    }

    public String getBuildingAddress() {
        return buildingAddress;
    }

    public void setBuildingAddress(String buildingAddress) {
        this.buildingAddress = buildingAddress;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
