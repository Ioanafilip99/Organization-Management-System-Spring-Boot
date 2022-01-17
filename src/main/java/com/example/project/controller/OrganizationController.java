package com.example.project.controller;

import com.example.project.dto.*;
import com.example.project.mapper.*;
import com.example.project.model.*;
import com.example.project.service.OrganizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@Validated
@RequestMapping("/organization")
public class OrganizationController {

    private final OrganizationService organizationService;
    private final BuildingMapper buildingMapper;
    private final DepartmentMapper departmentMapper;
    private final EmployeeMapper employeeMapper;
    private final OrganizationMapper organizationMapper;
    private final ProjectMapper projectMapper;

    public OrganizationController(OrganizationService organizationService, BuildingMapper buildingMapper, DepartmentMapper departmentMapper, EmployeeMapper employeeMapper, OrganizationMapper organizationMapper, ProjectMapper projectMapper) {
        this.organizationService = organizationService;
        this.buildingMapper = buildingMapper;
        this.departmentMapper = departmentMapper;
        this.employeeMapper = employeeMapper;
        this.organizationMapper = organizationMapper;
        this.projectMapper = projectMapper;
    }

    // GET all buildings
    @GetMapping("/building")
    ResponseEntity<List<Building>> retrieveBuildings(){
        return ResponseEntity.ok().body(organizationService.retrieveBuildings());
    }

    // GET building by id
    @GetMapping("/building/{buildingId}")
    ResponseEntity<Building> retrieveBuilding(@PathVariable int buildingId){
        return ResponseEntity.ok().body(organizationService.retrieveBuilding(buildingId));
    }

    // GET all departments
    @GetMapping("/department")
    ResponseEntity<List<Department>> retrieveDepartments(){
        return ResponseEntity.ok().body(organizationService.retrieveDepartments());
    }

    // GET department by id
    @GetMapping("/department/{departmentId}")
    ResponseEntity<Department> retrieveDepartment(@PathVariable int departmentId){
        return ResponseEntity.ok().body(organizationService.retrieveDepartment(departmentId));
    }

    // GET all employees
    @GetMapping("/employee")
    ResponseEntity<List<Employee>> retrieveEmployees(){
        return ResponseEntity.ok().body(organizationService.retrieveEmployees());
    }

    // GET employee by id
    @GetMapping("/employee/{employeeId}")
    ResponseEntity<Employee> retrieveEmployee(@PathVariable int employeeId){
        return ResponseEntity.ok().body(organizationService.retrieveEmployee(employeeId));
    }

    // GET all organizations
    @GetMapping("/")
    ResponseEntity<List<Organization>> retrieveOrganizations(){
        return ResponseEntity.ok().body(organizationService.retrieveOrganizations());
    }

    // GET organization by id
    @GetMapping("/{organizationId}")
    ResponseEntity<Organization> retrieveOrganization(@PathVariable int organizationId){
        return ResponseEntity.ok().body(organizationService.retrieveOrganization(organizationId));
    }

    // GET all projects
    @GetMapping("/project")
    ResponseEntity<List<Project>> retrieveProjects(){
        return ResponseEntity.ok().body(organizationService.retrieveProjects());
    }

    // GET project by id
    @GetMapping("/project/{projectId}")
    ResponseEntity<Project> retrieveProject(@PathVariable int projectId){
        return ResponseEntity.ok().body(organizationService.retrieveProject(projectId));
    }

    // POST new building
    @PostMapping("/building")
    ResponseEntity<?> saveBuilding(@RequestBody @Valid BuildingRequest buildingRequest){
        Building savedBuilding = organizationService.saveNewBuilding(buildingMapper.buildingRequestToBuilding(buildingRequest));
        return ResponseEntity.created(URI.create("organization/building/" + savedBuilding.getBuildingId()))
                             .body(savedBuilding);
    }

    // POST new organization
    @PostMapping("/")
    ResponseEntity<?> saveOrganization(@RequestBody @Valid OrganizationRequest organizationRequest,
                                       @RequestParam int buildingId){
        Organization savedOrganization = organizationService.saveNewOrganization(
                organizationMapper.organizationRequestToOrganization(organizationRequest),
                buildingId);
        return ResponseEntity.created(URI.create("organization/" + savedOrganization.getOrganizationId()))
                             .body(savedOrganization);
    }

    // POST new department
    @PostMapping("/department")
    ResponseEntity<?> saveDepartment(@RequestBody @Valid DepartmentRequest departmentRequest,
                                     @RequestParam int organizationId){
        Department savedDepartment = organizationService.saveNewDepartment(
                departmentMapper.departmentRequestToDepartment(departmentRequest),
                organizationId);
        return ResponseEntity.created(URI.create("organization/department/" + savedDepartment.getDepartmentId()))
                             .body(savedDepartment);
    }

    // POST new project
    @PostMapping("/project")
    ResponseEntity<Project> saveProject(@RequestBody @Valid ProjectRequest projectRequest){
        Project savedProject = organizationService.saveNewProject(projectMapper.projectRequestToProject(projectRequest));
        return ResponseEntity.created(URI.create("organization/project/" + savedProject.getProjectId()))
                             .body(savedProject);
    }

    // POST new employee
    @PostMapping("/employee")
    ResponseEntity<?> saveEmployee(@RequestBody @Valid EmployeeRequest employeeRequest,
                                   @RequestParam int departmentId,
                                   @RequestParam List<Integer> projectIds){
        Employee savedEmployee = organizationService.saveNewEmployee(
                employeeMapper.employeeRequestToEmployee(employeeRequest),
                departmentId,
                projectIds);
        return ResponseEntity.created(URI.create("organization/employee/" + savedEmployee.getEmployeeId()))
                             .body(savedEmployee);
    }

    // DELETE building by id
    @DeleteMapping("/building/{buildingId}")
    ResponseEntity<String> deleteBuilding(@PathVariable int buildingId){
        return ResponseEntity.ok().body(organizationService.deleteBuilding(buildingId));
    }

    // DELETE department by id
    @DeleteMapping("/department/{departmentId}")
    ResponseEntity<String> deleteDepartment(@PathVariable int departmentId){
        return ResponseEntity.ok().body(organizationService.deleteDepartment(departmentId));
    }

    // DELETE employee by id
    @DeleteMapping("/employee/{employeeId}")
    ResponseEntity<String> deleteEmployee(@PathVariable int employeeId){
        return ResponseEntity.ok().body(organizationService.deleteEmployee(employeeId));
    }

    // DELETE organization by id
    @DeleteMapping("/organization/{organizationId}")
    ResponseEntity<String> deleteOrganization(@PathVariable int organizationId){
        return ResponseEntity.ok().body(organizationService.deleteOrganization(organizationId));
    }

    // DELETE project by id
    @DeleteMapping("/project/{projectId}")
    ResponseEntity<String> deleteProject(@PathVariable int projectId){
        return ResponseEntity.ok().body(organizationService.deleteProject(projectId));
    }

    // UPDATE employee's salary, given by id (increase salary)
    @PatchMapping("/employee/{employeeId}/salary/increase/{value}")
    ResponseEntity<?> employeeIncreaseSalaryBy(@PathVariable int employeeId, @PathVariable Double value){
        int result = organizationService.employeeIncreaseSalaryBy(employeeId, value);
        if(result == 1) {
            return ResponseEntity.ok().body("Employee salary was updated!");
        } else if(result == -1) {
            return ResponseEntity.badRequest().body("Employee new salary must be less or equal to 20000.00!");
        }
        return ResponseEntity.badRequest().body("Employee could not be updated!");
    }

    // UPDATE employee's salary, given by id (decrease salary)
    @PatchMapping("/employee/{employeeId}/salary/decrease/{value}")
    ResponseEntity<?> employeeDecreaseSalaryBy(@PathVariable int employeeId, @PathVariable Double value){
        int result = organizationService.employeeDecreaseSalaryBy(employeeId, value);
        if(result == 1) {
            return ResponseEntity.ok().body("Employee salary was updated!");
        } else if(result == -1) {
            return ResponseEntity.badRequest().body("Employee new salary must be more than or equal to 2000.00!");
        }
        return ResponseEntity.badRequest().body("Employee could not be updated!");
    }

    // GET employees by salary and optional by organization and/or department
    @GetMapping("/employee/filter")
    ResponseEntity<List<Employee>> filterEmployees(@RequestParam(required = false) Integer organizationId,
                                                 @RequestParam(required = false) Integer departmentId,
                                                 @RequestParam(required = false, defaultValue = "6000.00") Double salaryLimit){
        return ResponseEntity.ok().body(organizationService.filterEmployees(organizationId, departmentId, salaryLimit));
    }

    // UPDATE project given by id
    @PutMapping("/project/{projectId}")
    ResponseEntity<?> updateProject(@RequestBody @Valid ProjectRequest projectRequest,
                                    @PathVariable int projectId){
        return ResponseEntity.ok().body(organizationService.updateProject(
                projectMapper.projectRequestToProject(projectRequest),
                projectId));
    }
}
