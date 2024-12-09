package com.javaweb.buildingproject.controller;

import com.javaweb.buildingproject.annotation.ApiMessage;
import com.javaweb.buildingproject.domain.dto.PaginationDTO;
import com.javaweb.buildingproject.domain.dto.UserDTO;
import com.javaweb.buildingproject.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiMessage("fetch one user by id")
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> fetchUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.fetchById(id));
    }

    @ApiMessage("fetch one user by name")
    @GetMapping("/users/name")
    public ResponseEntity<?> getUserByUsername(@RequestParam String userName){
        return ResponseEntity.ok().body(userService.fetchUserByUserName(userName));
    }

    @ApiMessage("fetch all users")
    @GetMapping("/users")
    public ResponseEntity<PaginationDTO> fetchAllUsers(@RequestParam(defaultValue = "1") Integer pageNumber
                                                    , @RequestParam(defaultValue = "10") Integer pageSize){
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        PaginationDTO paginationDTO = userService.fetchAllUser(pageable);
        return ResponseEntity.ok().body(paginationDTO);
    }

    @ApiMessage("creat user")
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO){
        UserDTO createdUser = userService.createUser(userDTO);
        createdUser.setPassword("*********");
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @ApiMessage("Update successfully")
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO){
        return ResponseEntity.ok().body(userService.updateUser(id,userDTO));
    }

    @ApiMessage("Delete successfully")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
