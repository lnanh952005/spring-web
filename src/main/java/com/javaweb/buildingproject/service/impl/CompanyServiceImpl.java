package com.javaweb.buildingproject.service.impl;

import com.javaweb.buildingproject.converter.CompanyConverter;
import com.javaweb.buildingproject.domain.dto.CompanyDTO;
import com.javaweb.buildingproject.domain.entity.CompanyEntity;
import com.javaweb.buildingproject.exception.custom.NotFoundException;
import com.javaweb.buildingproject.repository.CompanyRepository;
import com.javaweb.buildingproject.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;
    private CompanyConverter companyConverter;

    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyConverter companyConverter) {
        this.companyRepository = companyRepository;
        this.companyConverter = companyConverter;
    }

    @Override
    public CompanyDTO fetchCompanyById(Long id) {
        Optional<CompanyEntity> companyEntity = companyRepository.findById(id);
        if(companyEntity.isPresent()) {
            return companyConverter.convertToDTO(companyEntity.get());
        }
        throw new NotFoundException("Company Not Found");
    }

    @Override
    public CompanyDTO fetchCompanyByName(String name) {
        Optional<CompanyEntity> companyEntity = companyRepository.findByName(name);
        if(companyEntity.isPresent()) {
            return companyConverter.convertToDTO(companyEntity.get());
        }
        throw new NotFoundException("Company Not Found");
    }

    @Override
    public List<CompanyDTO> fetchAllCompanies() {
        List<CompanyEntity> companyEntityList = companyRepository.findAll();
        List<CompanyDTO> companyDTOList = new ArrayList<>();
        for(CompanyEntity companyEntity : companyEntityList) {
            companyDTOList.add(companyConverter.convertToDTO(companyEntity));
        }
        return companyDTOList;
    }

    @Override
    public CompanyDTO createCompany(CompanyDTO companyRequest) {
        Optional<CompanyEntity> companyEntity = companyRepository.findByName(companyRequest.getName());
        if(companyEntity.isPresent()) {
            throw new NotFoundException("Company Already Exist");
        }
        return companyConverter.convertToDTO(companyRepository.save(companyConverter.convertToEntity(companyRequest)));
    }

    @Override
    public CompanyDTO updateCompany(Long id,CompanyDTO companyRequest) {
        CompanyDTO companyDTO = fetchCompanyById(id);
        if(companyDTO == null) {
            throw new NotFoundException("Company Not Found");
        }
        companyRepository.save(companyConverter.convertToEntity(companyRequest));
        return companyDTO;
    }

    @Override
    public void deleteCompany(Long id) {
        if(companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
        }
        throw new NotFoundException("Company Not Found");
    }
}
