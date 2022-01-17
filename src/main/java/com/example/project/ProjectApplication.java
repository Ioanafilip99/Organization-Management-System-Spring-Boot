package com.example.project;

import com.example.project.model.*;
import com.example.project.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Array;
import java.util.Arrays;

@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {

	private final BuildingRepository buildingRepository;
	private final DepartmentRepository departmentRepository;
	private final EmployeeRepository employeeRepository;
	private final OrganizationRepository organizationRepository;
	private final ProjectRepository projectRepository;

	public ProjectApplication(BuildingRepository buildingRepository, DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, OrganizationRepository organizationRepository, ProjectRepository projectRepository) {
		this.buildingRepository = buildingRepository;
		this.departmentRepository = departmentRepository;
		this.employeeRepository = employeeRepository;
		this.organizationRepository = organizationRepository;
		this.projectRepository = projectRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Building building1 = new Building("Address 1");
		buildingRepository.save(building1);

		Organization organization1 = new Organization("Organization 1");
		organization1.setBuilding(building1);
		organizationRepository.save(organization1);

		Department department1 = new Department("Name 1", "Description 1");
		Department department2 = new Department("Name 2", "Description 2");
		department1.setOrganization(organization1);
		department2.setOrganization(organization1);
		departmentRepository.save(department1);
		departmentRepository.save(department2);

		Project project1 = new Project("Title 1", "Description 1");
		Project project2 = new Project("Title 2", "Description 2");
		Project project3 = new Project("Title 3", "Description 3");
		projectRepository.save(project1);
		projectRepository.save(project2);
		projectRepository.save(project3);

		Employee employee1 = new Employee("Name 1", "email1@email.com", "1111111111", 7000.00);
		Employee employee2 = new Employee("Name 2", "email2@email.com", "2222222222", 5000.00);
		Employee employee3 = new Employee("Name 3", "email3@email.com", "3333333333", 8000.00);
		Employee employee4 = new Employee("Name 4", "email4@email.com", "4444444444", 6500.00);
		employee1.setDepartment(department1);
		employee2.setDepartment(department1);
		employee3.setDepartment(department2);
		employee4.setDepartment(department2);
		employee1.setProjectList(Arrays.asList(project1, project2));
		employee2.setProjectList(Arrays.asList(project3));
		employee3.setProjectList(Arrays.asList(project1, project3));
		employee4.setProjectList(Arrays.asList(project2));
		employeeRepository.save(employee1);
		employeeRepository.save(employee2);
		employeeRepository.save(employee3);
		employeeRepository.save(employee4);

	}
}
