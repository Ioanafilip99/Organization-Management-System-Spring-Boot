package com.example.project.repository;

import com.example.project.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, Integer> {
}
