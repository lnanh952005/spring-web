package com.javaweb.buildingproject.mapper;

import com.javaweb.buildingproject.domain.dto.SkillDTO;
import com.javaweb.buildingproject.domain.entity.SkillEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SkillMapper {
    @Autowired
    private ModelMapper modelMapper;

    public SkillDTO toDTO(SkillEntity skillEntity) {
        return modelMapper.map(skillEntity, SkillDTO.class);
    }

    public SkillEntity toEntity(SkillDTO skillDTO) {
        return modelMapper.map(skillDTO, SkillEntity.class);
    }
}
