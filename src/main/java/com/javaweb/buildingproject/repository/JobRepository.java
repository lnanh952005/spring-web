package com.javaweb.buildingproject.repository;

import com.javaweb.buildingproject.domain.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobRepository extends JpaRepository<JobEntity, Long> {
    Optional<JobEntity> findByName(String jobName);
}