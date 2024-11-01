package com.javaweb.buildingproject.service.impl;

import com.javaweb.buildingproject.domain.DTO.UserDTO;
import com.javaweb.buildingproject.converter.UserConverter;
import com.javaweb.buildingproject.domain.ResponseDTO.UserResponse;
import com.javaweb.buildingproject.domain.requestDTO.UserRequest;
import com.javaweb.buildingproject.entity.UserEntity;
import com.javaweb.buildingproject.repository.UserRepository;
import com.javaweb.buildingproject.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserConverter userConverter;

    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<UserEntity> userEntityList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for(UserEntity item : userEntityList){
            userDTOList.add(userConverter.convertToDTO(item));
        }
        return userDTOList;
    }

    @Override
    public UserResponse getById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return userConverter.convertToResponse(userEntity);
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(()->new RuntimeException("user not exits"));
        UserResponse userResponse = userConverter.convertToResponse(userEntity);
        userRepository.save(userEntity);
        return userResponse;
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        if(userRepository.existsByuserName(userRequest.getUserName()) || userRepository.existsByemail(userRequest.getEmail())){
            throw new RuntimeException("user name or email already existed");
        }
        UserEntity userEntity = userConverter.convertToEntity(userRequest);
        userRepository.save(userEntity);
        return userConverter.convertToResponse(userEntity);
    }
}
