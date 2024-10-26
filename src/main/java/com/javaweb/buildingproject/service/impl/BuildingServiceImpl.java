package com.javaweb.buildingproject.service.impl;

import com.javaweb.buildingproject.DTO.BuildingDTO;
import com.javaweb.buildingproject.entity.BuildingEntity;
import com.javaweb.buildingproject.repository.BuildingRepository;
import com.javaweb.buildingproject.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Override
    public List<BuildingDTO> getAllBuilding() {
        List<BuildingDTO> buildingDTOList = new ArrayList<>();
        List<BuildingEntity> buildingEntityList = buildingRepository.findAll();
        for(BuildingEntity item : buildingEntityList){
            BuildingDTO buildingDTO = new BuildingDTO(item.getName(),item.getNumberOfbasement(),item.getStreet()+","+item.getWard());
            buildingDTOList.add(buildingDTO);
        }
        return buildingDTOList;
    }

    @Override
    public List<BuildingDTO> findByName(String name) {
        List<BuildingEntity> buildingEntityList = buildingRepository.findAll();
        List<BuildingDTO> buildingDTOList = new ArrayList<>();
        for(BuildingEntity entity : buildingEntityList){
            if(entity.getName().toLowerCase().contains(name.toLowerCase())){
                BuildingDTO buildingDTO = new BuildingDTO(entity.getName(), entity.getNumberOfbasement(), entity.getStreet()+","+entity.getWard());
                buildingDTOList.add(buildingDTO);
            }
        }
        return buildingDTOList;
    }
}
