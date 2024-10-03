package com.javaweb.buildingproject.controller;

import com.javaweb.buildingproject.domain.buildingDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class user {

    @GetMapping(value = "/building")
    public List<buildingDTO> getBuilding(@RequestBody List<buildingDTO> dto){
        return dto;
    }
}
