package com.javaweb.buildingproject.controller;

import com.javaweb.buildingproject.DTO.BuildingDTO;

import com.javaweb.buildingproject.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @GetMapping(value = "/getAllBuilding")
    public List<BuildingDTO> getBuilding(){
        return buildingService.getAllBuilding();
    }

    @GetMapping(value = "/getByName")
    public List<BuildingDTO> getName(@RequestParam(value = "name") String name){
        return buildingService.findByName(name);
    }

    @GetMapping("/test")
    public String helloword(){
        return "ok";
    }
}
