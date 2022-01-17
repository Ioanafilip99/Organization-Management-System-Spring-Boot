package com.example.project.dto;

import com.example.project.model.Building;
import com.example.project.model.Department;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class OrganizationRequest {

    @NotNull(message = "Organization name can't be null!")
    @NotEmpty(message = "Organization name can't be empty!")
    private String organizationName;

    private Building building;

    private List<Department> departmentList = new ArrayList<>();

    public OrganizationRequest(String organizationName, Building building, List<Department> departmentList) {
        this.organizationName = organizationName;
        this.building = building;
        this.departmentList = departmentList;
    }

    public OrganizationRequest() {
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }
}
