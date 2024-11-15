package com.javaweb.buildingproject.controller;

import com.javaweb.buildingproject.domain.dto.CompanyDTO;

import com.javaweb.buildingproject.service.impl.CompanyService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/companies")
    public ResponseEntity<List<CompanyDTO>> fetchAll() {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.fetchAllCompanies());
    }

    @PostMapping("/companies")
    ResponseEntity<?> addCompany(@Valid @RequestBody CompanyDTO companyRequest) {
        CompanyDTO companyDTO = companyService.createCompany(companyRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(companyDTO);
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<?> updateCompany(@PathVariable Long id ,@RequestBody CompanyDTO companyDTO){
        return ResponseEntity.status(HttpStatus.OK).body(companyService.updateCompany(id, companyDTO));
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
