package com.javaweb.buildingproject.repository;

import com.javaweb.buildingproject.entity.BuildingEntity;
import java.util.List;
public interface BuildingRepository {
    List<BuildingEntity> findAll();
}
