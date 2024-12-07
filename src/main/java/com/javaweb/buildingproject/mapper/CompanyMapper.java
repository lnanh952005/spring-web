package com.javaweb.buildingproject.mapper;

import com.javaweb.buildingproject.domain.dto.CompanyDTO;
import com.javaweb.buildingproject.domain.entity.CompanyEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyMapper {

    @Autowired
    private ModelMapper modelMapper;

    public CompanyDTO toDTO(CompanyEntity companyEntity){
        return modelMapper.map(companyEntity, CompanyDTO.class);
    }

    public CompanyEntity toEntity(CompanyDTO companyDTO){
        return modelMapper.map(companyDTO, CompanyEntity.class);
    }


}
