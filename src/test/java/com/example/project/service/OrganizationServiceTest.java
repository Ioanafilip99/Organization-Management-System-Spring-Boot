package com.example.project.service;

import com.example.project.model.*;
import com.example.project.repository.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Array;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrganizationServiceTest {

    @InjectMocks
    private OrganizationService organizationService;

    @Mock
    private BuildingRepository buildingRepository;
    @Mock
    private DepartmentRepository departmentRepository;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private OrganizationRepository organizationRepository;
    @Mock
    private ProjectRepository projectRepository;


    @Test
    @DisplayName("Running retrieveBuildings in a happy flow")
    void retrieveBuildingsHappyFlow(){
        // arrange
        List<Building> buildingList = new ArrayList<>();
        when(buildingRepository.findAll()).thenReturn(buildingList);
        // act
        List<Building> result = organizationService.retrieveBuildings();
        // assert
        assertEquals(buildingList, result);
    }

    @Test
    @DisplayName("Running retrieveBuilding in a happy flow")
    void retrieveBuildingHappyFlow(){
        // arrange
        int buildingId = 1;
        Building building = new Building("Address Test");
        when(buildingRepository.findById(buildingId)).thenReturn(Optional.of(building));
        // act
        Building result = organizationService.retrieveBuilding(buildingId);
        // assert
        assertEquals(building.getBuildingAddress(), result.getBuildingAddress());
    }

    @Test
    @DisplayName("Running retrieveDepartments in a happy flow")
    void retrieveDepartmentsHappyFlow(){
        // arrange
        List<Department> departmentList = new ArrayList<>();
        when(departmentRepository.findAll()).thenReturn(departmentList);
        // act
        List<Department> result = organizationService.retrieveDepartments();
        // assert
        assertEquals(departmentList, result);
    }

    @Test
    @DisplayName("Running retrieveDepartment in a happy flow")
    void retrieveDepartmentHappyFlow(){
        // arrange
        int departmentId = 1;
        Department department = new Department("Name Test", "Desc Test");
        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(department));
        // act
        Department result = organizationService.retrieveDepartment(departmentId);
        // assert
        assertEquals(department.getDepartmentName(), result.getDepartmentName());
        assertEquals(department.getDepartmentDescription(), result.getDepartmentDescription());
    }

    @Test
    @DisplayName("Running retrieveEmployees in a happy flow")
    void retrieveEmployeesHappyFlow(){
        // arrange
        List<Employee> employeeList = new ArrayList<>();
        when(employeeRepository.findAll()).thenReturn(employeeList);
        // act
        List<Employee> result = organizationService.retrieveEmployees();
        // assert
        assertEquals(employeeList, result);
    }

    @Test
    @DisplayName("Running retrieveEmployee in a happy flow")
    void retrieveEmployeeHappyFlow(){
        // arrange
        int employeeId = 1;
        Employee employee = new Employee("Name Test", "Email Test", "Phone Test", 6000.00);
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        // act
        Employee result = organizationService.retrieveEmployee(employeeId);
        // assert
        assertEquals(employee.getEmployeeName(), result.getEmployeeName());
        assertEquals(employee.getEmployeeEmail(), result.getEmployeeEmail());
        assertEquals(employee.getEmployeePhoneNumber(), result.getEmployeePhoneNumber());
        assertEquals(employee.getEmployeeSalary(), result.getEmployeeSalary());
    }

    @Test
    @DisplayName("Running retrieveOrganizations in a happy flow")
    void retrieveOrganizationsHappyFlow(){
        // arrange
        List<Organization> organizationList = new ArrayList<>();
        when(organizationRepository.findAll()).thenReturn(organizationList);
        // act
        List<Organization> result = organizationService.retrieveOrganizations();
        // assert
        assertEquals(organizationList, result);
    }

    @Test
    @DisplayName("Running retrieveOrganization in a happy flow")
    void retrieveOrganizationHappyFlow(){
        // arrange
        int organizationId = 1;
        Organization organization = new Organization("Name Test");
        when(organizationRepository.findById(organizationId)).thenReturn(Optional.of(organization));
        // act
        Organization result = organizationService.retrieveOrganization(organizationId);
        // assert
        assertEquals(organization.getOrganizationName(), result.getOrganizationName());
    }

    @Test
    @DisplayName("Running retrieveProjects in a happy flow")
    void retrieveProjectsHappyFlow(){
        // arrange
        List<Project> projectList = new ArrayList<>();
        when(projectRepository.findAll()).thenReturn(projectList);
        // act
        List<Project> result = organizationService.retrieveProjects();
        // assert
        assertEquals(projectList, result);
    }

    @Test
    @DisplayName("Running retrieveProject in a happy flow")
    void retrieveProjectHappyFlow(){
        // arrange
        int projectId = 1;
        Project project = new Project("Title Test", "Desc Test");
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        // act
        Project result = organizationService.retrieveProject(projectId);
        // assert
        assertEquals(project.getProjectTitle(), result.getProjectTitle());
        assertEquals(project.getProjectDescription(), result.getProjectDescription());
    }

    @Test
    @DisplayName("Running saveNewBuilding in a happy flow")
    void saveNewBuildingHappyFlow(){
        // arrange
        Building building = new Building("Building Address Test");
        when(buildingRepository.save(building)).thenReturn(building);
        // act
        Building result = organizationService.saveNewBuilding(building);
        // assert
        assertEquals(building.getBuildingAddress(), result.getBuildingAddress());
    }

    @Test
    @DisplayName("Running saveNewDepartment in a happy flow")
    void saveNewDepartmentHappyFlow(){
        // arrange
        int organizationId = 1;
        Department department = new Department("Department Name Test", "Description Test");
        Organization organization = new Organization("Organization Name Test");

        when(organizationRepository.findById(organizationId)).thenReturn(Optional.of(organization));
        when(departmentRepository.save(department)).thenReturn(department);

        // act
        Department result = organizationService.saveNewDepartment(department, organizationId);

        // assert
        assertEquals(department.getDepartmentName(), result.getDepartmentName());
        assertEquals(department.getDepartmentDescription(), result.getDepartmentDescription());
        assertEquals(organization.getOrganizationName(), result.getOrganization().getOrganizationName());
    }

    @Test
    @DisplayName("Running saveNewDepartment in a negative flow")
    void saveNewDepartmentNegativeFlow(){
        // arrange
        int organizationId = 1;
        Department department = new Department("Department Name Test", "Description Test");

        when(organizationRepository
                .findById(organizationId))
                .thenThrow(new RuntimeException("Exception Message Test"));

        // act
        try {
            Department result = organizationService.saveNewDepartment(department, organizationId);
        } catch (RuntimeException e){
            assertEquals("Exception Message Test", e.getMessage());
            verify(organizationRepository, times(1)).findById(organizationId);
        }

        // assert
    }

    @Test
    @DisplayName("Running saveNewEmployee in a happy flow")
    void saveNewEmployeeHappyFlow(){
        // arrange
        int departmentId = 1;
        List<Integer> projectIds = Arrays.asList(new Integer[]{1,2});

        Employee employee = new Employee("Name1", "email1@email.com", "1111111111", 7000.00);
        Department department = new Department("Department Name 1", "Department Desc 1");
        Project project1 = new Project("Project Title 1", "Project Desc 1");
        Project project2 = new Project("Project Title 2", "Project Desc 2");
        List<Project> projectList = new ArrayList<>();
        projectList.add(project1);
        projectList.add(project2);

        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(department));
        when(projectRepository.findAllById(projectIds)).thenReturn(projectList);
        when(employeeRepository.save(employee)).thenReturn(employee);

        // act
        Employee result = organizationService.saveNewEmployee(employee, departmentId, projectIds);

        // assert
        assertEquals(employee.getEmployeeName(), result.getEmployeeName());
        assertEquals(employee.getEmployeeEmail(), result.getEmployeeEmail());
        assertEquals(employee.getEmployeePhoneNumber(), result.getEmployeePhoneNumber());
        assertEquals(employee.getEmployeeSalary(), result.getEmployeeSalary());
        assertEquals(department.getDepartmentName(), result.getDepartment().getDepartmentName());
        assertEquals(department.getDepartmentDescription(), result.getDepartment().getDepartmentDescription());
        assertEquals(project1.getProjectTitle(), result.getProjectList().get(0).getProjectTitle());
        assertEquals(project1.getProjectDescription(), result.getProjectList().get(0).getProjectDescription());
        assertEquals(project2.getProjectTitle(), result.getProjectList().get(1).getProjectTitle());
        assertEquals(project2.getProjectDescription(), result.getProjectList().get(1).getProjectDescription());
    }

    @Test
    @DisplayName("Running saveNewOrganization in a happy flow")
    void saveNewOrganizationHappyFlow(){
        // arrange
        int buildingId = 1;
        Organization organization = new Organization("Organization Name Test");
        Building building = new Building("Building Address Test");

        when(buildingRepository.findById(buildingId)).thenReturn(Optional.of(building));
        when(organizationRepository.save(organization)).thenReturn(organization);

        // act
        Organization result = organizationService.saveNewOrganization(organization, buildingId);

        // assert
        assertEquals(organization.getOrganizationName(), result.getOrganizationName());
        assertEquals(building.getBuildingAddress(), result.getBuilding().getBuildingAddress());
    }

    @Test
    @DisplayName("Running saveNewProject in a happy flow")
    void saveNewProjectHappyFlow(){
        // arrange
        Project project = new Project("Project Title Test", "Project Desc Test");
        when(projectRepository.save(project)).thenReturn(project);
        // act
        Project result = organizationService.saveNewProject(project);
        // assert
        assertEquals(project.getProjectTitle(), result.getProjectTitle());
        assertEquals(project.getProjectDescription(), result.getProjectDescription());
    }

    @Test
    @DisplayName("Running deleteBuilding in a happy flow")
    void deleteBuildingHappyFlow(){
        // arrange
        int buildingId = 1;
        Building building = new Building("Address Test");
        when(buildingRepository.findById(buildingId)).thenReturn(Optional.of(building));

        // act
        organizationService.deleteBuilding(buildingId);

        // assert
        verify(buildingRepository, times(1)).delete(building);

    }

    @Test
    @DisplayName("Running deleteDepartment in a happy flow")
    void deleteDepartmentHappyFlow(){
        // arrange
        int departmentId = 1;
        Department department = new Department("Name Test", "Desc Test");
        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(department));

        // act
        organizationService.deleteDepartment(departmentId);

        // assert
        verify(departmentRepository, times(1)).delete(department);

    }

    @Test
    @DisplayName("Running deleteEmployee in a happy flow")
    void deleteEmployeeHappyFlow(){
        // arrange
        int employeeId = 1;
        Employee employee = new Employee("Name Test", "Email Test", "Phone Test", 9000.00);
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        // act
        organizationService.deleteEmployee(employeeId);

        // assert
        verify(employeeRepository, times(1)).delete(employee);

    }

    @Test
    @DisplayName("Running deleteOrganization in a happy flow")
    void deleteOrganizationHappyFlow(){
        // arrange
        int organizationId = 1;
        Organization organization = new Organization("Name Test");
        when(organizationRepository.findById(organizationId)).thenReturn(Optional.of(organization));

        // act
        organizationService.deleteOrganization(organizationId);

        // assert
        verify(organizationRepository, times(1)).delete(organization);

    }

    @Test
    @DisplayName("Running deleteProject in a happy flow")
    void deleteProjectHappyFlow(){
        // arrange
        int projectId = 1;
        Project project = new Project("Title Test", "Desc Test");
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));

        // act
        organizationService.deleteProject(projectId);

        // assert
        verify(projectRepository, times(1)).delete(project);

    }

    @Test
    @DisplayName("Running employeeIncreaseSalaryBy in a negative flow")
    void employeeIncreaseSalaryByNegativeFlow(){
        // arrange
        int employeeId = 1;
        Double value = 1000.00;

        when(employeeRepository.findById(employeeId)).thenThrow(new RuntimeException("Exception Message Test"));

        // act
        try {
            int result = organizationService.employeeIncreaseSalaryBy(employeeId, value);
        } catch (RuntimeException e){
            assertEquals("Exception Message Test", e.getMessage());
            verify(employeeRepository, times(1)).findById(employeeId);
            verify(employeeRepository, times(0)).employeeIncreaseSalaryBy(employeeId, value);
        }

        // assert
    }

    @Test
    @DisplayName("Running employeeDecreaseSalaryBy in a negative flow")
    void employeeDecreaseSalaryByByNegativeFlow(){
        // arrange
        int employeeId = 1;
        Double value = 3000.00;
        Employee employee = new Employee("Name Test", "Email Test", "Phone Test", 4000.00);

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        // act
        int result = organizationService.employeeDecreaseSalaryBy(employeeId, value);

        // assert
        assertEquals(-1, result);
        verify(employeeRepository, times(0)).employeeDecreaseSalaryBy(employeeId, value);
    }

    @Test
    @DisplayName("Running filterEmployees in a negative flow")
    void filterEmployeesNegativeFlow(){
        // arrange
        Integer organizationId = 1;
        Integer departmentId = 1;
        Double salaryLimit = 5000.00;

        when(organizationRepository.existsById(organizationId)).thenThrow(new RuntimeException("Exception Message Test"));

        // act
        try {
            List<Employee> result = organizationService.filterEmployees(organizationId, departmentId, salaryLimit);
        } catch (RuntimeException e){
            assertEquals("Exception Message Test", e.getMessage());
            verify(organizationRepository, times(1)).existsById(organizationId);
            verify(departmentRepository, times(0)).existsById(departmentId);
            verify(employeeRepository, times(0)).employeeFilterBySalary(salaryLimit);
            verify(employeeRepository, times(0)).employeeFilterByDepartmentSalary(departmentId, salaryLimit);
            verify(employeeRepository, times(0)).employeeFilterByOrganizationDepartmentSalary(organizationId, departmentId, salaryLimit);
        }

        // assert
    }

    @Test
    @DisplayName("Running updateProject in a happy flow")
    void updateProjectHappyFlow(){
        // arrange
        int projectId = 1;
        Project project = new Project("Title Test", "Desc Test");
        Project newProject = new Project("New Title Test", "New Desc Test");

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(projectRepository.updateProject(newProject.getProjectTitle(), newProject.getProjectDescription(), projectId))
                .thenReturn(1);

        // act
        int result = organizationService.updateProject(newProject, projectId);

        // assert
        assertEquals(1, result);

    }
}
