package com.example.project.dto;

import com.example.project.model.Department;
import com.example.project.model.Project;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.project.model.Pattern.EMAIL_PATTERN;
import static com.example.project.model.Pattern.PHONE_PATTERN;

public class EmployeeRequest {

    @NotNull(message = "Employee name can't be null!")
    @NotEmpty(message = "Employee name can't be empty!")
    private String employeeName;

    @NotNull(message = "Employee email can't be null!")
    @NotEmpty(message = "Employee email can't be empty!")
    @Pattern(regexp = EMAIL_PATTERN, message = "Employee email format is invalid!")
    private String employeeEmail;

    @NotNull(message = "Employee phone number can't be null!")
    @NotEmpty(message = "Employee phone number can't be empty!")
    @Pattern(regexp = PHONE_PATTERN, message = "Employee phone number format is invalid!")
    private String employeePhoneNumber;

    @DecimalMin(value = "2000.00", message = "Employee salary can't be lower than 2000.00!")
    @DecimalMax(value = "20000.00", message = "Employee salary can't be greater than 20000.00!")
    private double employeeSalary;

    private Department department;

    private List<Project> projectList = new ArrayList<>();

    public EmployeeRequest(String employeeName, String employeeEmail, String employeePhoneNumber, double employeeSalary, Department department, List<Project> projectList) {
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.employeePhoneNumber = employeePhoneNumber;
        this.employeeSalary = employeeSalary;
        this.department = department;
        this.projectList = projectList;
    }

    public EmployeeRequest() {
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
