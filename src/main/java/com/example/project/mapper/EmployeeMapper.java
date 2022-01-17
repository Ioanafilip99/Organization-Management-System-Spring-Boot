package com.example.project.mapper;

import com.example.project.dto.EmployeeRequest;
import com.example.project.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public Employee employeeRequestToEmployee(EmployeeRequest employeeRequest){
        return new Employee(employeeRequest.getEmployeeName(), employeeRequest.getEmployeeEmail(),
                employeeRequest.getEmployeePhoneNumber(), employeeRequest.getEmployeeSalary());
    }
}
