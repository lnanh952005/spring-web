package com.javaweb.buildingproject.service.impl;

import com.javaweb.buildingproject.domain.Response.Meta;
import com.javaweb.buildingproject.domain.dto.ResultPaginationDTO;
import com.javaweb.buildingproject.domain.dto.UserDTO;
import com.javaweb.buildingproject.converter.UserConverter;
import com.javaweb.buildingproject.domain.entity.UserEntity;
import com.javaweb.buildingproject.exception.custom.NotFoundException;
import com.javaweb.buildingproject.repository.UserRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService{

    private UserRepository userRepository;
    private UserConverter userConverter;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserConverter userConverter, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO fetchUserByUserName(String username) {
        Optional<UserEntity> userEntity = userRepository.findByusername(username);
        if(userEntity.isEmpty()){
            throw new NotFoundException("user not found");
        }
        return userConverter.convertToDTO(userEntity.get());
    }

    public ResultPaginationDTO fetchAllUser(Specification<UserEntity> specification,Pageable pageable) {
        Page<UserEntity> userEntityPage = userRepository.findAll(specification,pageable);
        List<UserEntity> userEntityList = userEntityPage.getContent();
        ResultPaginationDTO resultPaginationDTO = new ResultPaginationDTO();
        Meta meta = new Meta();

        meta.setPage(pageable.getPageNumber());
        meta.setPageSize(pageable.getPageSize());

        meta.setPages(userEntityPage.getTotalPages());
        meta.setTotal(userEntityPage.getTotalElements());

        resultPaginationDTO.setMeta(meta);
        List<UserDTO> userDTOList = new ArrayList<>();
        for(UserEntity item : userEntityList){
            userDTOList.add(userConverter.convertToDTO(item));
        }
        resultPaginationDTO.setResult(userDTOList);
        return resultPaginationDTO;
    }

    public UserDTO fetchById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        return userConverter.convertToDTO(userEntity);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(()->new NotFoundException("user not found"));
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPhone(userDTO.getPhone());
        userEntity.setFullname(userDTO.getFullname());
        userRepository.save(userEntity);
        return userDTO;
    }

    public UserDTO createUser(UserDTO userDTO) {
        if(userRepository.existsByusername(userDTO.getUsername()) || userRepository.existsByemail(userDTO.getEmail())){
            throw new NotFoundException("user name or email already existed");
        }
        UserEntity userEntity = userConverter.convertToEntity(userDTO);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
        return userConverter.convertToDTO(userEntity);
    }

    public void deleteUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(userEntity);
    }

}
