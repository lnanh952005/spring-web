package com.javaweb.buildingproject.service;

import com.javaweb.buildingproject.domain.dto.BuildingDTO;

import java.util.List;

public interface BuildingService {
    BuildingDTO fetchById(Long id);
    List<BuildingDTO> fetchAllBuilding();
    BuildingDTO insertBuilding(BuildingDTO buildingDTO);
    BuildingDTO updateBuilding(Long id,BuildingDTO buildingDTO);
}

