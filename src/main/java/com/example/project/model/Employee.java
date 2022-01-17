package com.example.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    private String employeeName;
    private String employeeEmail;
    private String employeePhoneNumber;
    private double employeeSalary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany
    @JoinTable(name = "employee_project", joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    @JsonIgnore
    private List<Project> projectList = new ArrayList<>();

    public Employee() {
    }

    public Employee(String employeeName, String employeeEmail, String employeePhoneNumber, double employeeSalary) {
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.employeePhoneNumber = employeePhoneNumber;
        this.employeeSalary = employeeSalary;
    }

    public Employee(String employeeName, String employeeEmail, String employeePhoneNumber, double employeeSalary, Department department, List<Project> projectList) {
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.employeePhoneNumber = employeePhoneNumber;
        this.employeeSalary = employeeSalary;
        this.department = department;
        this.projectList = projectList;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeePhoneNumber() {
        return employeePhoneNumber;
    }

    public void setEmployeePhoneNumber(String employeePhoneNumber) {
        this.employeePhoneNumber = employeePhoneNumber;
    }

    public double getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(double employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }
}
