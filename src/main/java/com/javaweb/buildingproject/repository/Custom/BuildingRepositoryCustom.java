package com.javaweb.buildingproject.repository.Custom;

import com.javaweb.buildingproject.entity.BuildingEntity;

import java.util.List;

public interface BuildingRepositoryCustom {
    List<BuildingEntity> findByNumberOfBasement(Long numberOfBasement);
}
