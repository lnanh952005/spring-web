package com.javaweb.buildingproject.repository.Impl;

import com.javaweb.buildingproject.domain.entity.BuildingEntity;
import com.javaweb.buildingproject.repository.Custom.BuildingRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BuildingEntity> findByNumberOfBasement(Long start) {
        String jpql = "SELECT u FROM BuildingEntity u WHERE u.numberOfbasement >= :start";
        return entityManager.createQuery(jpql, BuildingEntity.class).setParameter("start", start).getResultList();
    }
}
