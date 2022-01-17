package com.example.project.model;

import javax.persistence.*;

@Entity
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int buildingId;

    private String buildingAddress;

    @OneToOne(mappedBy = "building")
    private Organization organization;

    public Building() {
    }

    public Building(String buildingAddress) {
        this.buildingAddress = buildingAddress;
    }

    public Building(String buildingAddress, Organization organization) {
        this.buildingAddress = buildingAddress;
        this.organization = organization;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
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
