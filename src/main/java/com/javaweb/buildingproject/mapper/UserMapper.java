package com.javaweb.buildingproject.mapper;

import com.javaweb.buildingproject.domain.dto.UserDTO;
import com.javaweb.buildingproject.domain.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO ToDTO(UserEntity userEntity){
        return modelMapper.map(userEntity, UserDTO.class);
    }
    public UserEntity ToEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, UserEntity.class);
    }
}
