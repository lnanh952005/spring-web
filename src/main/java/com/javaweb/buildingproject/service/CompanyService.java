package com.javaweb.buildingproject.service;

import com.javaweb.buildingproject.domain.dto.PaginationDTO;
import com.javaweb.buildingproject.domain.entity.UserEntity;
import com.javaweb.buildingproject.exception.custom.ExistException;
import com.javaweb.buildingproject.mapper.CompanyMapper;
import com.javaweb.buildingproject.domain.dto.CompanyDTO;
import com.javaweb.buildingproject.domain.entity.CompanyEntity;
import com.javaweb.buildingproject.exception.custom.NotFoundException;
import com.javaweb.buildingproject.repository.CompanyRepository;
import com.javaweb.buildingproject.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService{
    private CompanyRepository companyRepository;
    private CompanyMapper companyMapper;
    private UserRepository userRepository;

    public CompanyService(CompanyRepository companyRepository, CompanyMapper companyMapper, UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
        this.userRepository = userRepository;
    }

    public CompanyDTO fetchCompanyById(Long id) {
        CompanyEntity companyEntity = companyRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Company not found with id: " + id));
        return companyMapper.toDTO(companyEntity);
    }

    public CompanyDTO fetchCompanyByName(String name) {
        CompanyEntity companyEntity = companyRepository.findByName(name)
                .orElseThrow(()-> new NotFoundException("Company not found with name: " + name));
        return companyMapper.toDTO(companyEntity);
    }

    public PaginationDTO fetchAllCompanies(Pageable pageable) {
        Page<CompanyEntity> page = companyRepository.findAll(pageable);
        PaginationDTO paginationDTO = new PaginationDTO();
        PaginationDTO.Meta meta = new PaginationDTO.Meta();
        List<CompanyEntity> companyEntityList = page.getContent();
        List<CompanyDTO> companyDTOList = companyEntityList.stream()
                .map(e->companyMapper.toDTO(e)).collect(Collectors.toList());
        meta.setPageNumber(pageable.getPageNumber()+1);
        meta.setPageSize(pageable.getPageSize());
        meta.setPages(page.getTotalPages());
        meta.setTotal(page.getTotalElements());
        paginationDTO.setMeta(meta);
        paginationDTO.setResult(companyDTOList);
        return paginationDTO;
    }

    public CompanyDTO createCompany(CompanyDTO companyRequest) {
        boolean exist = companyRepository.existsByName(companyRequest.getName());
        if(exist)
            throw new ExistException("Company Already Exist by name: " + companyRequest.getName());
        CompanyEntity companyEntity = companyRepository.save(companyMapper.toEntity(companyRequest));
        return companyMapper.toDTO(companyEntity);
    }

    public CompanyDTO updateCompany(Long id,CompanyDTO companyRequest) {
        CompanyEntity companyEntity = companyRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Company Not Found"));
        companyEntity.setName(companyRequest.getName());
        companyEntity.setAddress(companyRequest.getAddress());
        companyEntity.setDescription(companyRequest.getDescription());
        companyEntity.setLogo(companyRequest.getLogo());
        companyRepository.save(companyEntity);
        return companyMapper.toDTO(companyEntity);
    }

    public void deleteCompany(Long id) {
        CompanyEntity companyEntity = companyRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Company not found with id: " + id));
        List<UserEntity> userEntityList = userRepository.findByCompany(companyEntity);
        userRepository.deleteAll(userEntityList);
        companyRepository.deleteById(id);
    }
}
