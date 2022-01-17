package com.example.project.repository;

import com.example.project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    @Modifying
    @Query(value = "UPDATE project SET project_title = :projectTitle, project_description = :projectDescription WHERE project_id = :projectId", nativeQuery = true)    // JPQL
    int updateProject(String projectTitle, String projectDescription, int projectId);
}
