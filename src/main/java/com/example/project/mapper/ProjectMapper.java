package com.example.project.mapper;

import com.example.project.dto.ProjectRequest;
import com.example.project.model.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    public Project projectRequestToProject(ProjectRequest projectRequest){
        return new Project(projectRequest.getProjectTitle(), projectRequest.getProjectDescription());
    }
}
