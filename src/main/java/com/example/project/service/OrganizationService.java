package com.example.project.service;

import com.example.project.exceptions.InvalidIdException;
import com.example.project.model.*;
import com.example.project.repository.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrganizationService {

    private final BuildingRepository buildingRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final OrganizationRepository organizationRepository;
    private final ProjectRepository projectRepository;

    public OrganizationService(BuildingRepository buildingRepository, DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, OrganizationRepository organizationRepository, ProjectRepository projectRepository) {
        this.buildingRepository = buildingRepository;
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.organizationRepository = organizationRepository;
        this.projectRepository = projectRepository;
    }

    public List<Building> retrieveBuildings(){
        return buildingRepository.findAll();
    }

    public Building retrieveBuilding(int id){
        Building building = buildingRepository.findById(id)
                .orElseThrow(() -> new InvalidIdException("Building id is not valid!"));
        return building;
    }

    public List<Department> retrieveDepartments(){
        return departmentRepository.findAll();
    }

    public Department retrieveDepartment(int id){
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new InvalidIdException("Department id is not valid!"));
        return department;
    }

    public List<Employee> retrieveEmployees(){
        return employeeRepository.findAll();
    }

    public Employee retrieveEmployee(int id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new InvalidIdException("Employee id is not valid!"));
        return employee;
    }

    public List<Organization> retrieveOrganizations(){
        return organizationRepository.findAll();
    }

    public Organization retrieveOrganization(int id){
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new InvalidIdException("Organization id is not valid!"));
        return organization;
    }

    public List<Project> retrieveProjects(){
        return projectRepository.findAll();
    }

    public Project retrieveProject(int id){
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new InvalidIdException("Project id is not valid!"));
        return project;
    }

    public Building saveNewBuilding(Building building){
        return buildingRepository.save(building);
    }

    public Department saveNewDepartment(Department department, int organizationId){
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new InvalidIdException("Organization id is not valid!"));
        department.setOrganization(organization);

        return departmentRepository.save(department);
    }

    public Employee saveNewEmployee(Employee employee, int departmentId, List<Integer> projectIds){
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new InvalidIdException("Department id is not valid!"));
        employee.setDepartment(department);

        List<Project> projectList = projectRepository.findAllById(projectIds);
        employee.setProjectList(projectList);

        return employeeRepository.save(employee);
    }

    public Organization saveNewOrganization(Organization organization, int buildingId){
        Building building = buildingRepository.findById(buildingId)
                .orElseThrow(() -> new InvalidIdException("Building id is not valid!"));
        organization.setBuilding(building);

        return organizationRepository.save(organization);
    }

    public Project saveNewProject(Project project){
        return projectRepository.save(project);
    }

    public String deleteBuilding(int buildingId){
        Building building = buildingRepository.findById(buildingId)
                        .orElseThrow(() -> new InvalidIdException("Building id is not valid!"));
        buildingRepository.delete(building);

        return "Building with id " + buildingId + " succesfully deleted!";
    }

    public String deleteDepartment(int departmentId){
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new InvalidIdException("Department id is not valid!"));
        departmentRepository.delete(department);

        return "Department with id " + departmentId + " successfully deleted!";
    }

    public String deleteEmployee(int employeeId){
        Employee employee = employeeRepository.findById(employeeId)
                        .orElseThrow(() -> new InvalidIdException("Employee id is not valid!"));
        employeeRepository.delete(employee);

        return "Employee with id " + employeeId + " successfully deleted!";
    }

    public String deleteOrganization(int organizationId){
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new InvalidIdException("Organization id is not valid!"));
        organizationRepository.delete(organization);

        return "Organization with id " + organizationId + " successfully deleted!";
    }

    public String deleteProject(int projectId){
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new InvalidIdException("Project id is not valid!"));
        projectRepository.delete(project);

        return "Project with id " + projectId + " successfully deleted!";
    }

    @Transactional
    @Modifying
    public int employeeIncreaseSalaryBy(int employeeId, Double value){
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new InvalidIdException("Employee id is not valid!"));

        Double salary = employee.getEmployeeSalary();
        Double newSalary = salary + value;

        if(newSalary > 20000.00) {
            return -1;
        }
        return employeeRepository.employeeIncreaseSalaryBy(employeeId, value);
    }

    @Transactional
    @Modifying
    public int employeeDecreaseSalaryBy(int employeeId, Double value){
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new InvalidIdException("Employee id is not valid!"));

        Double salary = employee.getEmployeeSalary();
        Double newSalary = salary - value;

        if(newSalary < 2000.00) {
            return -1;
        }
        return employeeRepository.employeeDecreaseSalaryBy(employeeId, value);
    }


    public List<Employee> filterEmployees(Integer organizationId, Integer departmentId, Double salaryLimit) {
        if(organizationId != null){
            if(!organizationRepository.existsById(organizationId)){
                throw new InvalidIdException("Organization id is not valid!");
            }
            if(departmentId != null){
                if(!departmentRepository.existsById(departmentId)){
                    throw new InvalidIdException("Department id is not valid!");
                }
                return employeeRepository.employeeFilterByOrganizationDepartmentSalary(organizationId, departmentId, salaryLimit);
            } else {
                return employeeRepository.employeeFilterByOrganizationSalary(organizationId, salaryLimit);
            }
        } else if(departmentId != null){
            if(!departmentRepository.existsById(departmentId)){
                throw new InvalidIdException("Department id is not valid!");
            }
            return employeeRepository.employeeFilterByDepartmentSalary(departmentId, salaryLimit);
        }

        return  employeeRepository.employeeFilterBySalary(salaryLimit);
    }

    @Transactional
//    @Modifying
    public int updateProject(Project project, int projectId){
        Project oldProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new InvalidIdException("Project id is not valid!"));

        return projectRepository.updateProject(project.getProjectTitle(), project.getProjectDescription(), projectId);
    }
}
