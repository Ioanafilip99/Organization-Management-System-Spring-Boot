package com.example.project.controller;

import com.example.project.mapper.*;
import com.example.project.model.*;
import com.example.project.repository.*;
import com.example.project.service.OrganizationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrganizationController.class)
public class OrganizationControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BuildingMapper buildingMapper;
    @MockBean
    private BuildingRepository buildingRepository;

    @MockBean
    private DepartmentMapper departmentMapper;
    @MockBean
    private DepartmentRepository departmentRepository;

    @MockBean
    private EmployeeMapper employeeMapper;
    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private OrganizationMapper organizationMapper;
    @MockBean
    private OrganizationRepository organizationRepository;

    @MockBean
    private ProjectMapper projectMapper;
    @MockBean
    private ProjectRepository projectRepository;


    @MockBean
    private OrganizationService organizationService;

    @Test
    public void saveBuilding() throws Exception{
        // arrange
        Building building = new Building("Address Test");

        // act
        when(organizationService.saveNewBuilding(any())).thenReturn(building);

        // assert
        mockMvc.perform(post("/organization/building")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(building)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.buildingAddress").value(building.getBuildingAddress()));
    }

    @Test
    public void saveOrganization() throws Exception{
        // arrange
        Organization organization = new Organization("Name Test");
        String buildingId = "1";

        // act
        when(organizationService.saveNewOrganization(any(), anyInt())).thenReturn(organization);

        // assert
        mockMvc.perform(post("/organization/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(organization))
                        .param("buildingId", buildingId))

                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.organizationName").value(organization.getOrganizationName()));
    }

    @Test
    public void saveDepartment() throws Exception{
        // arrange
        Department department = new Department("Name Test", "Desc Test");
        String organizationId = "1";

        // act
        when(organizationService.saveNewDepartment(any(), anyInt())).thenReturn(department);

        // assert
        mockMvc.perform(post("/organization/department")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(department))
                        .param("organizationId", organizationId))

                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.departmentName").value(department.getDepartmentName()))
                .andExpect(jsonPath("$.departmentDescription").value(department.getDepartmentDescription()));
    }

    @Test
    public void saveProject() throws Exception{
        // arrange
        Project project = new Project("Title Test", "Desc Test");

        // act
        when(organizationService.saveNewProject(any())).thenReturn(project);

        // assert
        mockMvc.perform(post("/organization/project")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(project)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.projectTitle").value(project.getProjectTitle()))
                .andExpect(jsonPath("$.projectDescription").value(project.getProjectDescription()));
    }

    @Test
    public void saveEmployee() throws Exception{
        // arrange
        Employee employee = new Employee("Name Test", "email@test.com", "1111111111", 7000.00);
        String departmentId = "1";

        // act
        when(organizationService.saveNewEmployee(any(), anyInt(), any())).thenReturn(employee);

        // assert
        mockMvc.perform(post("/organization/employee")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(employee))
                        .param("departmentId", departmentId)
                        .param("projectIds", new String[]{"1", "2"}))

                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.employeeName").value(employee.getEmployeeName()))
                .andExpect(jsonPath("$.employeeEmail").value(employee.getEmployeeEmail()))
                .andExpect(jsonPath("$.employeePhoneNumber").value(employee.getEmployeePhoneNumber()))
                .andExpect(jsonPath("$.employeeSalary").value(employee.getEmployeeSalary()));
    }
}
