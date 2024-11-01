package com.javaweb.buildingproject.service;

import com.javaweb.buildingproject.domain.DTO.BuildingDTO;

import java.util.List;

public interface BuildingService {
    List<BuildingDTO> getByName(String name);
    List<BuildingDTO> getAllBuilding();
    List<BuildingDTO> getByNumberOfBasement(Long start);
}
