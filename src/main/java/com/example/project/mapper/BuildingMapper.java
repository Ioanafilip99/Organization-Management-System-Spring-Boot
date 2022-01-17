package com.example.project.mapper;

import com.example.project.dto.BuildingRequest;
import com.example.project.model.Building;
import org.springframework.stereotype.Component;

@Component
public class BuildingMapper {
    public Building buildingRequestToBuilding(BuildingRequest buildingRequest){
        return new Building(buildingRequest.getBuildingAddress());
    }

}
