package com.javaweb.buildingproject.repository;

import com.javaweb.buildingproject.domain.entity.SkillEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<SkillEntity, Long> {
    Page<SkillEntity> findAll(Pageable pageable);
    Optional<SkillEntity> findByName(String name);
    boolean existsByName(String name);
}
