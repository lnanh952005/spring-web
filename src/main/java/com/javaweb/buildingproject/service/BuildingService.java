package com.javaweb.buildingproject.service;

import com.javaweb.buildingproject.converter.BuildingConverter;
import com.javaweb.buildingproject.domain.dto.BuildingDTO;
import com.javaweb.buildingproject.domain.entity.BuildingEntity;
import com.javaweb.buildingproject.exception.custom.NotFoundException;
import com.javaweb.buildingproject.repository.BuildingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BuildingService {

    private BuildingRepository buildingRepository;
    private BuildingConverter buildingConverter;

    public BuildingService(BuildingRepository buildingRepository, BuildingConverter buildingConverter){
        this.buildingRepository = buildingRepository;
        this.buildingConverter = buildingConverter;
    }

    public BuildingDTO fetchById(Long id) {
        Optional<BuildingEntity> buildingEntity = buildingRepository.findById(id);
        if(buildingEntity.isEmpty()){
            throw new NotFoundException("building not found");
        }
        return buildingEntity.map(o->buildingConverter.convertToDTO(o)).get();
    }

    public List<BuildingDTO> fetchAllBuilding() {
        List<BuildingDTO> buildingDTOList = new ArrayList<>();
        List<BuildingEntity> buildingEntityList = buildingRepository.findAll();
        for(BuildingEntity item : buildingEntityList){
            buildingDTOList.add(buildingConverter.convertToDTO(item));
        }
        return buildingDTOList;
    }

    public BuildingDTO insertBuilding(BuildingDTO buildingDTO) {
        if(buildingRepository.existsByname(buildingDTO.getName())){
            throw new NotFoundException("Building is already existed");
        }
        BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
        buildingRepository.save(buildingEntity);
        return buildingDTO;
    }

    public BuildingDTO updateBuilding(Long id,BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingRepository.findById(id).orElseThrow(()-> new NotFoundException("building not exist"));
        buildingEntity.setName(buildingDTO.getName());
        buildingEntity.setStreet(buildingDTO.getStreet());
        buildingEntity.setWard(buildingDTO.getWard());
        buildingEntity.setFloorArea(buildingDTO.getFloorArea());
        buildingEntity.setNumberOfBasement(buildingDTO.getNumberOfBasement());
        buildingRepository.save(buildingEntity);
        return buildingDTO;
    }

}
