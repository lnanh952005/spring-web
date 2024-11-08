package com.javaweb.buildingproject.converter;

import com.javaweb.buildingproject.domain.dto.UserDTO;
import com.javaweb.buildingproject.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO convertToDTO(UserEntity userEntity){
        return modelMapper.map(userEntity,UserDTO.class);
    }

    public UserEntity convertToEntity(UserDTO userDTO){
        return modelMapper.map(userDTO,UserEntity.class);
    }



}
