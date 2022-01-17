package com.example.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int organizationId;

    private String organizationName;

    @OneToOne
    @JoinColumn(name = "building_id")
    @JsonIgnore
    private Building building;

    @OneToMany(mappedBy = "organization")
    @JsonIgnore
    private List<Department> departmentList = new ArrayList<>();

    public Organization() {
    }

    public Organization(String organizationName) {
        this.organizationName = organizationName;
    }

    public Organization(String organizationName, Building building, List<Department> departmentList) {
        this.organizationName = organizationName;
        this.building = building;
        this.departmentList = departmentList;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
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
