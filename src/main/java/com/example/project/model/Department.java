package com.example.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int departmentId;

    private String departmentName;
    private String departmentDescription;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<Employee> employeeList = new ArrayList<>();

    public Department() {
    }

    public Department(String departmentName, String departmentDescription) {
        this.departmentName = departmentName;
        this.departmentDescription = departmentDescription;
    }

    public Department(String departmentName, String departmentDescription, Organization organization, List<Employee> employeeList) {
        this.departmentName = departmentName;
        this.departmentDescription = departmentDescription;
        this.organization = organization;
        this.employeeList = employeeList;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
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
