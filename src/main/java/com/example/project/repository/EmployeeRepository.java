package com.example.project.repository;

import com.example.project.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Modifying
    @Query("UPDATE Employee e SET e.employeeSalary = e.employeeSalary + ?2 WHERE e.employeeId = ?1")    // JPQL
    int employeeIncreaseSalaryBy(int employeeId, Double value);

    @Modifying
    @Query("UPDATE Employee e SET e.employeeSalary = e.employeeSalary - ?2 WHERE e.employeeId = ?1")    // JPQL
    int employeeDecreaseSalaryBy(int employeeId, Double value);

    @Query(value = "SELECT * \n" +
                    "FROM employee e\n" +
                    "JOIN department d ON e.department_id = d.department_id\n" +
                    "JOIN organization o ON d.organization_id = o.organization_id\n" +
                    "WHERE e.department_id = :departmentId AND o.organization_id = :organizationId AND e.employee_salary >= :salaryLimit\n" +
                    "ORDER BY e.employee_name", nativeQuery = true)            // native
    List<Employee> employeeFilterByOrganizationDepartmentSalary(Integer organizationId, Integer departmentId, Double salaryLimit);

    @Query(value = "SELECT * \n" +
                    "FROM employee e\n" +
                    "JOIN department d ON e.department_id = d.department_id\n" +
                    "JOIN organization o ON d.organization_id = o.organization_id\n" +
                    "WHERE o.organization_id = :organizationId AND e.employee_salary >= :salaryLimit\n" +
                    "ORDER BY e.employee_name", nativeQuery = true)            // native
    List<Employee> employeeFilterByOrganizationSalary(Integer organizationId, Double salaryLimit);

    @Query(value = "SELECT * \n" +
                    "FROM employee e\n" +
                    "JOIN department d ON e.department_id = d.department_id\n" +
                    "WHERE e.department_id = :departmentId AND e.employee_salary >= :salaryLimit\n" +
                    "ORDER BY e.employee_name", nativeQuery = true)            // native
    List<Employee> employeeFilterByDepartmentSalary(Integer departmentId, Double salaryLimit);

    @Query(value = "SELECT * \n" +
                    "FROM employee e\n" +
                    "WHERE e.employee_salary >= :salaryLimit\n" +
                    "ORDER BY e.employee_name", nativeQuery = true)            // native
    List<Employee> employeeFilterBySalary(Double salaryLimit);
}
