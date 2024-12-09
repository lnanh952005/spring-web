package com.javaweb.buildingproject.repository;

import com.javaweb.buildingproject.domain.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long> {
    Optional<JobEntity> findByName(String jobName);
    boolean existsByName(String jobName);
}