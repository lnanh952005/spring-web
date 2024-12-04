package com.javaweb.buildingproject.controller;

import com.javaweb.buildingproject.annotation.ApiMessage;
import com.javaweb.buildingproject.domain.request.LoginDTO;
import com.javaweb.buildingproject.domain.dto.RestLoginDTO;

import com.javaweb.buildingproject.repository.UserRepository;
import com.javaweb.buildingproject.service.AuthService;
import com.javaweb.buildingproject.service.UserService;
import com.javaweb.buildingproject.utils.JwtUtils;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
    @Value("${hoidanit.jwt.base64-secret}")
    private String secretKey;

    @Value("${hoidanit.jwt.refresh-token-validity-in-seconds}")
    private long refreshTokenExpiration;

    private JwtUtils jwtUtils;
    private UserService userService;
    private AuthService authService;
    private UserRepository userRepository;

    public AuthController(JwtDecoder jwtDecoder, JwtUtils jwtUtils, UserService userService, AuthService authService, UserRepository userRepository) {
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @ApiMessage("login successfully")
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) throws JOSEException {
        RestLoginDTO restLoginDTO = authService.authenticateAndGenerateAccessToken(loginDTO);
        String refreshToken = authService.generateAndSaveRefreshToken(loginDTO);
        ResponseCookie responseCookie = ResponseCookie
                .from("refreshtoken",refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(refreshTokenExpiration)
                .build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,responseCookie.toString()).body(restLoginDTO);

    }

    @GetMapping("/auth/refresh")
    @ApiMessage("Refresh Access Token")
    public ResponseEntity<?> refreshAccessToken(@CookieValue(name = "refreshtoken") String refreshToken) throws MissingRequestValueException, JOSEException {
        if(refreshToken.isEmpty()){
            throw new MissingRequestValueException("bạn không có token ở cookie");
        }
        RestLoginDTO restLoginDTO = authService.checkRefreshTokenAndgetNewAccessToken(refreshToken);
        String newRefreshToken = jwtUtils.createRefreshToken(restLoginDTO.getUserlogin().getUsername(),restLoginDTO.getUserlogin());
        userService.updateRefreshToken(newRefreshToken,restLoginDTO.getUserlogin().getUsername());
        // set refreshToken mới
        ResponseCookie responseCookie = ResponseCookie
                .from("refreshtoken",newRefreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(refreshTokenExpiration)
                .build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,responseCookie.toString()).body(restLoginDTO);
    }


    @PostMapping("auth/logout")
    @ApiMessage("user logout")
    public ResponseEntity<Void> logout(@CookieValue(name = "refreshtoken") String refreshToken) {
        authService.logout(refreshToken);
        ResponseCookie deleteCookie = ResponseCookie
                .from("refreshtoken",null)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)
                .build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,deleteCookie.toString()).body(null);
    }

}
