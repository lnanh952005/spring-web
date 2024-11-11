package com.javaweb.buildingproject.service;

import com.javaweb.buildingproject.domain.dto.CompanyDTO;

import java.util.List;

public interface CompanyService {
    CompanyDTO fetchCompanyById(Long id);
    CompanyDTO fetchCompanyByName(String name);
    List<CompanyDTO> fetchAllCompanies();
    CompanyDTO createCompany(CompanyDTO companyDTO);
    CompanyDTO updateCompany(Long id,CompanyDTO companyDTO);
    void deleteCompany(Long id);
}
