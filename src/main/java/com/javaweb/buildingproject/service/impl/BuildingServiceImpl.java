package com.javaweb.buildingproject.service.impl;

import com.javaweb.buildingproject.domain.DTO.BuildingDTO;
import com.javaweb.buildingproject.entity.BuildingEntity;
import com.javaweb.buildingproject.repository.BuildingRepository;
import com.javaweb.buildingproject.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Override
    public List<BuildingDTO> getAllBuilding() {
        List<BuildingDTO> buildingDTOList = new ArrayList<>();
        List<BuildingEntity> buildingEntityList = buildingRepository.findAll();
        for(BuildingEntity item : buildingEntityList){
            BuildingDTO buildingDTO = new BuildingDTO();
            buildingDTO.setName(item.getName());
            buildingDTO.setAddress(item.getStreet() + ", "+ item.getWard());
            buildingDTO.setNumberOfbasement(item.getNumberOfbasement());
            buildingDTOList.add(buildingDTO);
        }
        return buildingDTOList;
    }

    @Override
    public List<BuildingDTO> getByNumberOfBasement(Long start) {
        List<BuildingEntity> buildingEntityList = buildingRepository.findByNumberOfBasement(start);
        List<BuildingDTO> buildingDTOList = new ArrayList<>();
        for(BuildingEntity item : buildingEntityList){
            BuildingDTO buildingDTO = new BuildingDTO();
            buildingDTO.setName(item.getName());
            buildingDTO.setAddress(item.getStreet() + ", "+ item.getWard());
            buildingDTO.setNumberOfbasement(item.getNumberOfbasement());
            buildingDTOList.add(buildingDTO);
        }
        return buildingDTOList;
    }

    @Override
    public List<BuildingDTO> getByName(String name) {
        List<BuildingEntity> buildingEntityList = buildingRepository.findAll();
        List<BuildingDTO> buildingDTOList = new ArrayList<>();
        for(BuildingEntity item : buildingEntityList){
            if(item.getName().toLowerCase().contains(name.toLowerCase())){
                BuildingDTO buildingDTO = new BuildingDTO();
                buildingDTO.setName(item.getName());
                buildingDTO.setAddress(item.getStreet() + ", "+ item.getWard());
                buildingDTO.setNumberOfbasement(item.getNumberOfbasement());
                buildingDTOList.add(buildingDTO);
            }
        }
        return buildingDTOList;
    }
}
