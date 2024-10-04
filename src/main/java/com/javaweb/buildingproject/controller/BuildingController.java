package com.javaweb.buildingproject.controller;

import com.javaweb.buildingproject.DTO.BuildingDTO;
import com.javaweb.buildingproject.service.BuildingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BuildingController {

    private BuildingService buildingservice;

    public BuildingController(BuildingService buildingservice){
        this.buildingservice = buildingservice;
    }

    @GetMapping(value = "/building")
    public List<BuildingDTO> getBuilding(){
        List<BuildingDTO> buildingDTOS = buildingservice.findAll();
        return buildingDTOS;
    }
}
