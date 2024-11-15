package com.javaweb.buildingproject.service;

import com.javaweb.buildingproject.domain.dto.ResultPaginationDTO;
import com.javaweb.buildingproject.domain.dto.UserDTO;
import com.javaweb.buildingproject.domain.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface UserService {
    UserDTO fetchUserByUserName(String username);
    ResultPaginationDTO fetchAllUser(Specification<UserEntity> specification, Pageable pageable);
    UserDTO fetchById(Long id);
    UserDTO createUser(UserDTO userRequest);
    UserDTO updateUser(Long id, UserDTO userRequest);
    void deleteUserById(Long id);
}
