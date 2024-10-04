package com.javaweb.buildingproject.service.impl;

import com.javaweb.buildingproject.DTO.BuildingDTO;
import com.javaweb.buildingproject.entity.BuildingEntity;
import com.javaweb.buildingproject.repository.BuildingRepository;
import com.javaweb.buildingproject.service.BuildingService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuidingServiceImpl implements BuildingService {

    private BuildingRepository buildingrepository;

    public BuidingServiceImpl(BuildingRepository buildingrepository) {
        this.buildingrepository = buildingrepository;
    }

    @Override
    public List<BuildingDTO> findAll() {
        List<BuildingDTO> buildingDTOS = new ArrayList<>();
        List<BuildingEntity> buildingEntities = buildingrepository.findAll();

        for(BuildingEntity item : buildingEntities){
            BuildingDTO dto = new BuildingDTO();
            dto.setName(item.getName());
            dto.setAddress(item.getStreet() +" "+item.getWard());
            dto.setNumberOfbasement(item.getNumberofbasement());
            buildingDTOS.add(dto);
        }
        return buildingDTOS;
    }
}
