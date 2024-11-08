package com.javaweb.buildingproject.service;

import com.javaweb.buildingproject.domain.dto.UserDTO;

import java.util.List;;

public interface UserService {
    UserDTO fetchUserByUserName(String username);
    List<UserDTO> fetchAllUser();
    UserDTO fetchById(Long id);
    UserDTO createUser(UserDTO userRequest);
    UserDTO updateUser(Long id, UserDTO userRequest);
    void deleteUserById(Long id);
}
