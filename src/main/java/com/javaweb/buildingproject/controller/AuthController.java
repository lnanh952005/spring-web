package com.javaweb.buildingproject.controller;

import com.javaweb.buildingproject.domain.dto.LoginDTO;
import com.javaweb.buildingproject.domain.dto.RestLoginDTO;
import com.javaweb.buildingproject.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<RestLoginDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        RestLoginDTO restLoginDTO = authService.login(loginDTO);
        return ResponseEntity.status(HttpStatus.OK).body(restLoginDTO);
    }
}
