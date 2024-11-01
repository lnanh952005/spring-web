package com.javaweb.buildingproject.controller;

import com.javaweb.buildingproject.domain.DTO.UserDTO;
import com.javaweb.buildingproject.domain.ResponseDTO.UserResponse;
import com.javaweb.buildingproject.domain.requestDTO.UserRequest;
import com.javaweb.buildingproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> userDTOList = userService.getAllUser();
        return new ResponseEntity<>(userDTOList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        UserResponse userResponse = userService.getById(id);
        return new ResponseEntity<>(userResponse,HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest){
        UserResponse userResponse = userService.createUser(userRequest);
        return new ResponseEntity<>(userResponse,HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody UserRequest userRequest){
        return new ResponseEntity<>(userService.updateUser(id,userRequest),HttpStatus.OK);
    }
}
