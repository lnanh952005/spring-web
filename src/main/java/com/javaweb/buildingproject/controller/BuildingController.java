package com.javaweb.buildingproject.controller;

import com.javaweb.buildingproject.domain.DTO.BuildingDTO;

import com.javaweb.buildingproject.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @GetMapping(value = "/buildings")
    public ResponseEntity<?> getAllBuilding(Model model){
        List<BuildingDTO> buildingDTOList = buildingService.getAllBuilding();
        model.addAttribute("buildings",buildingDTOList);
        return new ResponseEntity<>(buildingDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/buildings/name")
    public List<BuildingDTO> getByName(@RequestParam(value = "name") String name){
        return buildingService.getByName(name);
    }

    @GetMapping(value = "/buildings/numberofbasements")
    public List<BuildingDTO> getBuildingByNumberOfBasement(@RequestParam(value = "start") Long start){
        return buildingService.getByNumberOfBasement(start);
    }

}
