package com.javaweb.buildingproject.repository;

import com.javaweb.buildingproject.domain.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<CompanyEntity,Long> {
    Optional<CompanyEntity> findByName(String name);
}
