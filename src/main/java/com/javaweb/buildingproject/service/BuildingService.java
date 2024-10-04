package com.javaweb.buildingproject.service;

import com.javaweb.buildingproject.DTO.BuildingDTO;

import java.util.List;

public interface BuildingService {
    List<BuildingDTO> findAll();
}
