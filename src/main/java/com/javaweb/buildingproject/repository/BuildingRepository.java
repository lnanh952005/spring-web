package com.javaweb.buildingproject.repository;

import com.javaweb.buildingproject.domain.entity.BuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BuildingRepository extends JpaRepository<BuildingEntity,Long> {
    boolean existsByname(String name);
    Optional<BuildingEntity> findByname(String name);
}
