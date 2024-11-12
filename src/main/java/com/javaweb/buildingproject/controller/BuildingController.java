package com.javaweb.buildingproject.controller;

import com.javaweb.buildingproject.domain.dto.BuildingDTO;
import com.javaweb.buildingproject.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/buildings")
@ResponseBody
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @GetMapping("/{id}")
    public ResponseEntity<BuildingDTO> fetchBuildingsByid(@PathVariable("id") Long id){
        return new ResponseEntity<>(buildingService.fetchById(id),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> fetchAllBuildings(){
        return new ResponseEntity<>(buildingService.fetchAllBuilding(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insertBuilding(BuildingDTO buildingDTO){
        return new ResponseEntity<>(buildingService.insertBuilding(buildingDTO),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBuilding(@PathVariable("id") Long id,BuildingDTO buildingDTO){
        return ResponseEntity.status(HttpStatus.OK).body(buildingService.updateBuilding(id,buildingDTO));
    }
}
