package com.javaweb.buildingproject.controller;

import com.javaweb.buildingproject.domain.ResponseDTO.RestResponse;
import com.javaweb.buildingproject.domain.dto.UserDTO;
import com.javaweb.buildingproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/name")
    public ResponseEntity<?> getUserByUsername(@RequestParam String name){
        return ResponseEntity.status(HttpStatus.OK).body(userService.fetchUserByUserName(name));
    }
    @GetMapping
    public ResponseEntity<List<UserDTO>> fetchAllUsers(ModelMap modelMap){
        List<UserDTO> userDTOList = userService.fetchAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(userDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> fetchUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.fetchById(id));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.createUser(userDTO),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id,@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.updateUser(id,userDTO),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("user deleted");
    }
}
