package com.javaweb.buildingproject.service;

import com.javaweb.buildingproject.converter.CompanyConverter;
import com.javaweb.buildingproject.domain.dto.CompanyDTO;
import com.javaweb.buildingproject.domain.entity.CompanyEntity;
import com.javaweb.buildingproject.exception.custom.NotFoundException;
import com.javaweb.buildingproject.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService{
    private CompanyRepository companyRepository;
    private CompanyConverter companyConverter;

    public CompanyService(CompanyRepository companyRepository, CompanyConverter companyConverter) {
        this.companyRepository = companyRepository;
        this.companyConverter = companyConverter;
    }

    public CompanyDTO fetchCompanyById(Long id) {
        Optional<CompanyEntity> companyEntity = companyRepository.findById(id);
        if(companyEntity.isPresent()) {
            return companyConverter.convertToDTO(companyEntity.get());
        }
        throw new NotFoundException("Company Not Found");
    }

    public CompanyDTO fetchCompanyByName(String name) {
        Optional<CompanyEntity> companyEntity = companyRepository.findByName(name);
        if(companyEntity.isPresent()) {
            return companyConverter.convertToDTO(companyEntity.get());
        }
        throw new NotFoundException("Company Not Found");
    }

    public List<CompanyDTO> fetchAllCompanies() {
        List<CompanyEntity> companyEntityList = companyRepository.findAll();
        List<CompanyDTO> companyDTOList = new ArrayList<>();
        for(CompanyEntity companyEntity : companyEntityList) {
            companyDTOList.add(companyConverter.convertToDTO(companyEntity));
        }
        return companyDTOList;
    }

    public CompanyDTO createCompany(CompanyDTO companyRequest) {
        Optional<CompanyEntity> companyEntity = companyRepository.findByName(companyRequest.getName());
        if(companyEntity.isPresent()) {
            throw new NotFoundException("Company Already Exist");
        }
        return companyConverter.convertToDTO(companyRepository.save(companyConverter.convertToEntity(companyRequest)));
    }

    public CompanyDTO updateCompany(Long id,CompanyDTO companyRequest) {
        Optional<CompanyEntity> companyEntityOptional = companyRepository.findById(id);
        if(companyEntityOptional.isEmpty()) {
            throw new NotFoundException("Company Not Found");
        }
        CompanyEntity companyEntity = companyEntityOptional.get();
        companyEntity.setName(companyRequest.getName());
        companyEntity.setAddress(companyRequest.getAddress());
        companyEntity.setDescription(companyRequest.getDescription());
        companyEntity.setLogo(companyRequest.getLogo());
        companyRepository.save(companyEntity);
        return companyRequest;
    }

    public void deleteCompany(Long id) {
        if(!companyRepository.existsById(id)) {
            throw new NotFoundException("Company Not Found");
        }
        companyRepository.deleteById(id);
    }
}
