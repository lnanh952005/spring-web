package com.javaweb.buildingproject.repository.Impl;

import com.javaweb.buildingproject.entity.BuildingEntity;
import com.javaweb.buildingproject.repository.BuildingRepository;
import com.javaweb.buildingproject.repository.Custom.BuildingRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BuildingEntity> findByNumberOfBasement(Long numberOfBasement) {
        return null;
    }
}
