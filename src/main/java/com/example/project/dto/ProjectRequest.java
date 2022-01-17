package com.example.project.dto;

import com.example.project.model.Employee;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class ProjectRequest {

    @NotNull(message = "Project title can't be null!")
    @NotEmpty(message = "Project title can't be empty!")
    private String projectTitle;

    @NotNull(message = "Project description can't be null!")
    @NotEmpty(message = "Project description can't be empty!")
    private String projectDescription;

    private List<Employee> employeeList = new ArrayList<>();

    public ProjectRequest(String projectTitle, String projectDescription, List<Employee> employeeList) {
        this.projectTitle = projectTitle;
        this.projectDescription = projectDescription;
        this.employeeList = employeeList;
    }

    public ProjectRequest() {
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
