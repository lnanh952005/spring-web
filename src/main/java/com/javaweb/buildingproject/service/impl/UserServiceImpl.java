package com.javaweb.buildingproject.service.impl;

import com.javaweb.buildingproject.domain.dto.UserDTO;
import com.javaweb.buildingproject.converter.UserConverter;
import com.javaweb.buildingproject.entity.UserEntity;
import com.javaweb.buildingproject.exception.custom.NotFoundException;
import com.javaweb.buildingproject.repository.UserRepository;
import com.javaweb.buildingproject.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserConverter userConverter;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO fetchUserByUserName(String username) {
        Optional<UserEntity> userEntity = userRepository.findByuserName(username);
        if(userEntity.isEmpty()){
            throw new NotFoundException("user not found");
        }
        return userConverter.convertToDTO(userEntity.get());
    }

    @Override
    public List<UserDTO> fetchAllUser() {
        List<UserEntity> userEntityList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for(UserEntity item : userEntityList){
            userDTOList.add(userConverter.convertToDTO(item));
        }
        return userDTOList;
    }

    @Override
    public UserDTO fetchById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        return userConverter.convertToDTO(userEntity);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(()->new NotFoundException("user not found"));
        userEntity.setPassWord(userDTO.getPassWord());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPhone(userDTO.getPhone());
        userEntity.setFullName(userDTO.getFullName());
        userRepository.save(userEntity);
        return userDTO;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if(userRepository.existsByuserName(userDTO.getUserName()) || userRepository.existsByemail(userDTO.getEmail())){
            throw new NotFoundException("user name or email already existed");
        }
        UserEntity userEntity = userConverter.convertToEntity(userDTO);
        userEntity.setPassWord(passwordEncoder.encode(userEntity.getPassWord()));
        userRepository.save(userEntity);
        return userConverter.convertToDTO(userEntity);
    }

    @Override
    public void deleteUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(userEntity);
    }


}
