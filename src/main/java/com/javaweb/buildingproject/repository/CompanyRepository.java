package com.javaweb.buildingproject.repository;

import com.javaweb.buildingproject.domain.entity.CompanyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity,Long>, JpaSpecificationExecutor<CompanyEntity> {
    Page<CompanyEntity> findAll(Pageable pageable);
    Optional<CompanyEntity> findByName(String name);
    Optional<CompanyEntity> findById(Long id);
}
