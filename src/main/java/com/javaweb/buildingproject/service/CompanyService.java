package com.javaweb.buildingproject.service;

import com.javaweb.buildingproject.domain.dto.PaginationDTO;
import com.javaweb.buildingproject.mapper.CompanyMapper;
import com.javaweb.buildingproject.domain.dto.CompanyDTO;
import com.javaweb.buildingproject.domain.entity.CompanyEntity;
import com.javaweb.buildingproject.exception.custom.NotFoundException;
import com.javaweb.buildingproject.repository.CompanyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService{
    private CompanyRepository companyRepository;
    private CompanyMapper companyMapper;

    public CompanyService(CompanyRepository companyRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    public CompanyDTO fetchCompanyById(Long id) {
        Optional<CompanyEntity> companyEntity = companyRepository.findById(id);
        if(companyEntity.isPresent()) {
            return companyMapper.toDTO(companyEntity.get());
        }
        throw new NotFoundException("Company Not Found by id: " + id);
    }

    public CompanyDTO fetchCompanyByName(String name) {
        Optional<CompanyEntity> companyEntity = companyRepository.findByName(name);
        if(companyEntity.isPresent()) {
            return companyMapper.toDTO(companyEntity.get());
        }
        throw new NotFoundException("Company Not Found by name: " + name);
    }

    public PaginationDTO fetchAllCompanies(Pageable pageable) {
        Page<CompanyEntity> page = companyRepository.findAll(pageable);
        PaginationDTO paginationDTO = new PaginationDTO();
        PaginationDTO.Meta meta = new PaginationDTO.Meta();
        List<CompanyEntity> companyEntityList = page.getContent();
        List<CompanyDTO> companyDTOList = companyMapper.toDTOs(companyEntityList);
        meta.setPage(pageable.getPageNumber()+1);
        meta.setPageSize(pageable.getPageSize());
        meta.setPages(page.getTotalPages());
        meta.setTotal(page.getTotalElements());
        paginationDTO.setMeta(meta);
        paginationDTO.setResult(companyDTOList);
        return paginationDTO;
    }

    public CompanyDTO createCompany(CompanyDTO companyRequest) {
        Optional<CompanyEntity> companyEntity = companyRepository.findByName(companyRequest.getName());
        if(companyEntity.isPresent()) {
            throw new NotFoundException("Company Already Exist by name: " + companyRequest.getName());
        }
        companyRepository.save(companyMapper.toEntity(companyRequest));
        return companyRequest;
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
