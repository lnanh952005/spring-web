package com.javaweb.buildingproject.converter;

import com.javaweb.buildingproject.domain.dto.CompanyDTO;
import com.javaweb.buildingproject.domain.entity.CompanyEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyConverter {
    @Autowired
    private ModelMapper modelMapper;

    public CompanyDTO convertToDTO(CompanyEntity companyEntity) {
        return modelMapper.map(companyEntity,CompanyDTO.class);
    }

    public CompanyEntity convertToEntity(CompanyDTO companyDTO) {
        return modelMapper.map(companyDTO,CompanyEntity.class);
    }
}
