package com.javaweb.buildingproject.service;

import com.javaweb.buildingproject.domain.entity.CompanyEntity;
import com.javaweb.buildingproject.exception.custom.ExistException;
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

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder
            , CompanyRepository companyRepository, CompanyMapper companyMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    public UserDTO fetchById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found by id: " + id));
        return userMapper.ToDTO(userEntity);
    }

    public UserDTO fetchUserByUserName(String username) {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(()->new NotFoundException("User not found by name: " + username));
        return userMapper.ToDTO(userEntity);
    }

    public PaginationDTO fetchAllUser(Pageable pageable) {
        Page<UserEntity> page = userRepository.findAll(pageable);
        List<UserDTO> userDTOList = page.getContent().stream().map(e -> userMapper.ToDTO(e)).collect(Collectors.toList());
        PaginationDTO paginationDTO = new PaginationDTO();
        PaginationDTO.Meta meta = new PaginationDTO.Meta();
        meta.setPageNumber(pageable.getPageNumber()+1);
        meta.setPageSize(pageable.getPageSize());

        meta.setPages(page.getTotalPages());
        meta.setTotal(page.getTotalElements());

        paginationDTO.setMeta(meta);
        paginationDTO.setResult(userDTOList);
        return paginationDTO;
    }

    public UserDTO updateUser(Long id,UserDTO userDTO) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(()->new NotFoundException("User not found with id: " + id));
        if(userDTO.getCompany() != null) {
            CompanyEntity companyEntity = companyRepository.findById(userDTO.getCompany().getId()).orElse(null);
            userEntity.setCompany(companyEntity==null ? userEntity.getCompany() : companyEntity);
        }
        userEntity.setFullname(userDTO.getFullname());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getUsername()));
        userEntity.setAge(userDTO.getAge());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPhone(userDTO.getPhone());
        userRepository.save(userEntity);
        return userMapper.ToDTO(userEntity);
    }

    public UserDTO createUser(UserDTO userDTO) {
        boolean exists = userRepository.existsByUsernameOrEmail(userDTO.getUsername(),userDTO.getEmail());
        if(exists == true) {
            throw new ExistException("user already exists");
        }
        if(userDTO.getCompany() != null) {
            CompanyEntity companyEntity = companyRepository.findById(userDTO.getCompany().getId())
                    .orElseThrow(()->new NotFoundException("Company not found with id: " + userDTO.getCompany().getId()));
            userDTO.setCompany(companyMapper.toDTO(companyEntity));
        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        UserEntity userEntity = userRepository.save(userMapper.ToEntity(userDTO));
        return userMapper.ToDTO(userEntity);
    }

    public void deleteUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found by id: " + id));
        userRepository.delete(userEntity);
    }

    public void updateRefreshToken(String refreshToken, String username) {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(()->new NotFoundException("User not found with name: " + username));
        userEntity.setRefreshToken(refreshToken);
        userRepository.save(userEntity);
    }
}
