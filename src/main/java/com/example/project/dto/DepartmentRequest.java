package com.example.project.dto;

import com.example.project.model.Employee;
import com.example.project.model.Organization;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRequest {

    @NotNull(message = "Department name can't be null!")
    @NotEmpty(message = "Department name can't be empty!")
    private String departmentName;

    @NotNull(message = "Department description can't be null!")
    @NotEmpty(message = "Department description can't be empty!")
    private String departmentDescription;

    private Organization organization;

    private List<Employee> employeeList = new ArrayList<>();

    public DepartmentRequest() {
    }

    public DepartmentRequest(String departmentName, String departmentDescription, Organization organization, List<Employee> employeeList) {
        this.departmentName = departmentName;
        this.departmentDescription = departmentDescription;
        this.organization = organization;
        this.employeeList = employeeList;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentDescription() {
        return departmentDescription;
    }

    public void setDepartmentDescription(String departmentDescription) {
        this.departmentDescription = departmentDescription;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
