//package com.javaweb.buildingproject.controller;
//
//import com.javaweb.buildingproject.domain.dto.LoginDTO;
//import com.javaweb.buildingproject.domain.dto.RestLoginDTO;
//import com.javaweb.buildingproject.utils.SecurityUtil;
//import jakarta.validation.Valid;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/login")
//public class AuthController {
//
//    private AuthenticationManagerBuilder authenticationManagerBuilder;
//    private SecurityUtil securityUtil;
//
//    public AuthController(AuthenticationManagerBuilder authenticationManagerBuilder,SecurityUtil securityUtil){
//        this.authenticationManagerBuilder = authenticationManagerBuilder;
//        this.securityUtil = securityUtil;
//    }
//
//    @PostMapping
//    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO){
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(loginDTO.getUserName(),loginDTO.getPassWord());
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//        //create token
//        String token = securityUtil.createToken(authentication);
//        RestLoginDTO restLoginDTO = new RestLoginDTO();
//        restLoginDTO.setAccessToken(token);
//        return ResponseEntity.status(HttpStatus.OK).body(restLoginDTO);
//    }
//}
