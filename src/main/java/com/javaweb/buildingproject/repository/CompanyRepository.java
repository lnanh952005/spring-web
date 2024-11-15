package com.javaweb.buildingproject.repository;

import com.javaweb.buildingproject.domain.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<CompanyEntity,Long>, JpaSpecificationExecutor<CompanyEntity> {
    Optional<CompanyEntity> findByName(String name);
}
