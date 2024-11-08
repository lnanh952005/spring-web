package com.javaweb.buildingproject.controller;

import com.javaweb.buildingproject.domain.dto.LoginDTO;
import com.javaweb.buildingproject.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthControllerGPT {

    private AuthService authService;

    public AuthControllerGPT(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        String token = authService.authenticateUser(loginDTO);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
