package com.javaweb.buildingproject.converter;

import com.javaweb.buildingproject.domain.DTO.UserDTO;
import com.javaweb.buildingproject.domain.ResponseDTO.UserResponse;
import com.javaweb.buildingproject.domain.requestDTO.UserRequest;
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

    public UserEntity convertToEntity(UserRequest userRequest){
        return modelMapper.map(userRequest,UserEntity.class);
    }

    public UserResponse convertToResponse(UserEntity userEntity){
        return modelMapper.map(userEntity,UserResponse.class);
    }
}
