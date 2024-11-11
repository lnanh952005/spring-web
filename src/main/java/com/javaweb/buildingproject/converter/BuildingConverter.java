package com.javaweb.buildingproject.converter;

import com.javaweb.buildingproject.domain.dto.BuildingDTO;
import com.javaweb.buildingproject.domain.entity.BuildingEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuildingConverter {
    @Autowired
    private ModelMapper modelMapper;

    public BuildingEntity convertToEntity(BuildingDTO buildingDTO){
        return modelMapper.map(buildingDTO,BuildingEntity.class);
    }

    public BuildingDTO convertToDTO(BuildingEntity buildingEntity){
        return modelMapper.map(buildingEntity,BuildingDTO.class);
    }
}
