package com.javaweb.buildingproject.controller;

import com.javaweb.buildingproject.domain.dto.CompanyDTO;
import com.javaweb.buildingproject.service.CompanyService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> fetchAll() {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.fetchAllCompanies());
    }

    @PostMapping
    ResponseEntity<?> addCompany(@Valid @RequestBody CompanyDTO companyRequest) {
        CompanyDTO companyDTO = companyService.createCompany(companyRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(companyDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCompany(@PathVariable Long id ,@RequestBody CompanyDTO companyDTO){
        return ResponseEntity.status(HttpStatus.OK).body(companyService.updateCompany(id, companyDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
