package com.javaweb.buildingproject.controller;

import com.javaweb.buildingproject.domain.dto.CompanyDTO;
import com.javaweb.buildingproject.service.CompanyService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    ResponseEntity<?> addCompany(@Valid @RequestBody CompanyDTO companyRequest) {
        CompanyDTO companyDTO = companyService.createCompany(companyRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(companyDTO);
    }
}
