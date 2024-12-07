package com.javaweb.buildingproject.repository;

import com.javaweb.buildingproject.domain.entity.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<SkillEntity, Long> {
    
}
