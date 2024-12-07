package com.javaweb.buildingproject.mapper;

import com.javaweb.buildingproject.domain.dto.JobDTO;
import com.javaweb.buildingproject.domain.entity.JobEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {
    @Autowired
    private ModelMapper modelMapper;

    public JobDTO toDTO(JobEntity entity) {
        return modelMapper.map(entity, JobDTO.class);
    }

    public JobEntity toEntity(JobDTO dto) {
        return modelMapper.map(dto, JobEntity.class);
    }

}
