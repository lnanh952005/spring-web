package com.javaweb.buildingproject.service;

import com.javaweb.buildingproject.domain.DTO.UserDTO;
import com.javaweb.buildingproject.domain.ResponseDTO.UserResponse;
import com.javaweb.buildingproject.domain.requestDTO.UserRequest;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUser();
    UserResponse getById(Long id);
    UserResponse createUser(UserRequest userRequest);
    UserResponse updateUser(Long id, UserRequest userRequest);
}
