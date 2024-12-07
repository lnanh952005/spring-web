package com.javaweb.buildingproject.service;

import com.javaweb.buildingproject.domain.entity.CompanyEntity;
import com.javaweb.buildingproject.mapper.CompanyMapper;
import com.javaweb.buildingproject.mapper.UserMapper;
import com.javaweb.buildingproject.repository.CompanyRepository;
import com.javaweb.buildingproject.domain.dto.PaginationDTO;
import com.javaweb.buildingproject.domain.dto.UserDTO;
import com.javaweb.buildingproject.domain.entity.UserEntity;
import com.javaweb.buildingproject.exception.custom.NotFoundException;
import com.javaweb.buildingproject.repository.UserRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService{

    private UserRepository userRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;
    private CompanyRepository companyRepository;
    private CompanyMapper companyMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, CompanyRepository companyRepository, CompanyMapper companyMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    public UserDTO fetchById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found by id: " + id));
        return userMapper.ToDTO(userEntity);
    }

    public UserDTO fetchUserByUserName(String username) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        if(userEntity.isEmpty()){
            throw new NotFoundException("user not found by " + username);
        }
        return userMapper.ToDTO(userEntity.get());
    }

    public PaginationDTO fetchAllUser(Pageable pageable) {
        Page<UserEntity> page = userRepository.findAll(pageable);
        List<UserDTO> userDTOList = page.getContent().stream().map(e -> userMapper.ToDTO(e)).collect(Collectors.toList());
        PaginationDTO paginationDTO = new PaginationDTO();
        PaginationDTO.Meta meta = new PaginationDTO.Meta();
        meta.setPage(pageable.getPageNumber()+1);
        meta.setPageSize(pageable.getPageSize());

        meta.setPages(page.getTotalPages());
        meta.setTotal(page.getTotalElements());

        paginationDTO.setMeta(meta);
        paginationDTO.setResult(userDTOList);
        return paginationDTO;
    }

    public UserDTO updateUser(UserDTO userDTO) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(userDTO.getUsername());
        if(userEntityOptional.isEmpty()){
            throw new NotFoundException("user not found by: " + userDTO.getUsername());
        }
        if(userDTO.getCompany() != null){
            Optional<CompanyEntity> companyEntityOptional = companyRepository.findById(userDTO.getCompany().getId());
            userDTO.setCompany(companyEntityOptional.isPresent() ? companyMapper.toDTO(companyEntityOptional.get()) : null);
        }
        UserEntity userEntity = userEntityOptional.get();
        userEntity.setFullname(userDTO.getFullname());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getUsername()));
        userEntity.setAge(userDTO.getAge());
        userEntity.setCompany(userDTO.getCompany() == null ? userEntity.getCompany() : companyMapper.toEntity(userDTO.getCompany()));
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPhone(userDTO.getPhone());
        userRepository.save(userEntity);
        return userMapper.ToDTO(userEntity);
    }

    public UserDTO createUser(UserDTO userDTO) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUsernameOrEmail(userDTO.getUsername(), userDTO.getEmail());
        if(userEntityOptional.isPresent()){
            throw new NotFoundException("user name or email already existed");
        }
        if(userDTO.getCompany() != null){
            Optional<CompanyEntity> companyEntityOp = companyRepository.findById(userDTO.getCompany().getId());
            userDTO.setCompany(companyEntityOp.isPresent() ? companyMapper.toDTO(companyEntityOp.get()) : null);
        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(userMapper.ToEntity(userDTO));
        return userDTO;
    }

    public void deleteUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found by id: " + id));
        userRepository.delete(userEntity);
    }

    public void updateRefreshToken(String refreshToken, String username) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        if(userEntity.isEmpty()){
            throw new NotFoundException("user not found by " + username);
        }
        userEntity.get().setRefreshToken(refreshToken);
        userRepository.save(userEntity.get());
    }

}
