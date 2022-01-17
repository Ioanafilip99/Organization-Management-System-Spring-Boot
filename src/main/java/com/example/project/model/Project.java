package com.example.project.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;

    private String projectTitle;
    private String projectDescription;

    @ManyToMany(mappedBy = "projectList")
    private List<Employee> employeeList = new ArrayList<>();

    public Project() {
    }

    public Project(String projectTitle, String projectDescription) {
        this.projectTitle = projectTitle;
        this.projectDescription = projectDescription;
    }

    public Project(String projectTitle, String projectDescription, List<Employee> employeeList) {
        this.projectTitle = projectTitle;
        this.projectDescription = projectDescription;
        this.employeeList = employeeList;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
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
