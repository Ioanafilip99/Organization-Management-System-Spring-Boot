package com.example.project.service;

import com.example.project.model.Building;
import com.example.project.repository.BuildingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("test")
public class OrganizationServiceIT {

    @Autowired
    private OrganizationService organizationService;

    @MockBean
    private BuildingRepository buildingRepository;

    @Test
    @DisplayName("Running saveNewBuilding in a happy flow")
    public void saveNewBuildingHappyFlow(){
        // arrange
        Building building = new Building("Building Address Test");
        when(buildingRepository.save(building)).thenReturn(building);
        // act
        Building result = organizationService.saveNewBuilding(building);
        // assert
        assertEquals(building.getBuildingAddress(), result.getBuildingAddress());
    }

}
