package com.javaweb.buildingproject.controller;

import com.javaweb.buildingproject.annotation.ApiMessage;
import com.javaweb.buildingproject.domain.dto.CompanyDTO;

import com.javaweb.buildingproject.service.CompanyService;
import jakarta.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @ApiMessage("fetch all companies")
    @GetMapping("/companies")
    public ResponseEntity<?> fetchAll(@RequestParam Optional<Integer> pageNumber
                                    , @RequestParam Optional<Integer> pageSize){
        int pageSize1 = pageSize.orElse(1);
        int pageNumber1 = pageNumber.orElse(10);
        Pageable pageable = PageRequest.of(pageNumber1-1,pageSize1);
        return ResponseEntity.status(HttpStatus.OK).body(companyService.fetchAllCompanies(pageable));
    }

    @ApiMessage("fetch companies by id")
    @GetMapping("/companies/{id}")
    public ResponseEntity<?> fetchById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.fetchCompanyById(id));
    }

    @ApiMessage("Add company successfully")
    @PostMapping("/companies")
    ResponseEntity<?> createCompany(@Valid @RequestBody CompanyDTO companyRequest) {
        CompanyDTO companyDTO = companyService.createCompany(companyRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(companyDTO);
    }

    @ApiMessage("Update company")
    @PutMapping("/companies/{id}")
    public ResponseEntity<?> updateCompany(@PathVariable Long id ,@RequestBody CompanyDTO companyDTO){
        return ResponseEntity.status(HttpStatus.OK).body(companyService.updateCompany(id, companyDTO));
    }

    @ApiMessage("Delete company")
    @DeleteMapping("/companies/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
