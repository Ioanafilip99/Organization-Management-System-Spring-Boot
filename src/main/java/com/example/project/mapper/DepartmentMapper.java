package com.example.project.mapper;

import com.example.project.dto.DepartmentRequest;
import com.example.project.model.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {
    public Department departmentRequestToDepartment(DepartmentRequest departmentRequest){
        return new Department(departmentRequest.getDepartmentName(), departmentRequest.getDepartmentDescription());
    }
}
