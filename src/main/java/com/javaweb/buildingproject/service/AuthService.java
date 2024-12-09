package com.javaweb.buildingproject.service;

import com.javaweb.buildingproject.domain.request.LoginDTO;
import com.javaweb.buildingproject.domain.dto.RestLoginDTO;
import com.javaweb.buildingproject.domain.dto.UserDTO;
import com.javaweb.buildingproject.domain.entity.UserEntity;
import com.javaweb.buildingproject.exception.custom.NotFoundException;
import com.javaweb.buildingproject.repository.UserRepository;
import com.javaweb.buildingproject.utils.JwtUtils;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.util.Optional;


@Service
public class AuthService {
    @Value("${hoidanit.jwt.base64-secret}")
    private String secretKey;

    private JwtUtils jwtUtils;
    private UserService userService;
    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;

    public AuthService(JwtUtils jwtUtils, UserService userService, UserRepository userRepository
        , AuthenticationManager authenticationManager) {
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    public RestLoginDTO generateAccessToken(LoginDTO loginDTO) throws JOSEException {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        RestLoginDTO.Userlogin userlogin = new RestLoginDTO.Userlogin();
        UserDTO userDTO = userService.fetchUserByUserName(loginDTO.getUsername());
        userlogin.setId(userDTO.getId());
        userlogin.setFullname(userDTO.getFullname());
        userlogin.setUsername(userDTO.getUsername());
        String accessToken = jwtUtils.createAccessToken(authentication.getName(),userlogin);
        RestLoginDTO restLoginDTO = new RestLoginDTO();
        restLoginDTO.setAccessToken(accessToken);
        restLoginDTO.setUserlogin(userlogin);
        return restLoginDTO;
    }

    public String generateRefreshToken(LoginDTO loginDTO) throws JOSEException {
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(loginDTO.getUsername());
        if(userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            RestLoginDTO.Userlogin userlogin = new RestLoginDTO.Userlogin();
            userlogin.setId(userEntity.getId());
            userlogin.setFullname(userEntity.getFullname());
            userlogin.setUsername(userEntity.getUsername());
            String refreshToken = jwtUtils.createRefreshToken(userlogin.getUsername(), userlogin);
            userEntity.setRefreshToken(refreshToken);
            userRepository.save(userEntity);
            return refreshToken;
        }
        throw new NotFoundException("user not found");
    }

    public RestLoginDTO checkRefreshTokenAndgetNewAccessToken(String refreshToken) throws JOSEException {
        Jwt decodedJwt = jwtDecoder(refreshToken);
        Optional<UserEntity> userEntityOptional = userRepository.findByRefreshTokenAndUsername(refreshToken, decodedJwt.getSubject());

        if(userEntityOptional.isEmpty()){
            throw new NotFoundException("refresh token không hợp lệ");
        }

        UserEntity userEntity = userEntityOptional.get();
        RestLoginDTO.Userlogin userlogin = new RestLoginDTO.Userlogin();
        userlogin.setId(userEntity.getId());
        userlogin.setFullname(userEntity.getFullname());
        userlogin.setUsername(userEntity.getUsername());
        userEntity.setRefreshToken(refreshToken);
        userRepository.save(userEntity);
        String newAccessToken = jwtUtils.createAccessToken(userlogin.getUsername(), userlogin);
        RestLoginDTO restLoginDTO = new RestLoginDTO();
        restLoginDTO.setAccessToken(newAccessToken);
        restLoginDTO.setUserlogin(userlogin);
        return restLoginDTO;
    }

    public void logout(String refreshtoken){
        Jwt decodedJwt = jwtDecoder(refreshtoken);
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(decodedJwt.getSubject());
        if(userEntityOptional.isPresent()){
            UserEntity userEntity = userEntityOptional.get();
            userEntity.setRefreshToken(null);
            userRepository.save(userEntity);
        }
    }

    public Jwt jwtDecoder(String refreshToken) {
        JwtDecoder jwtDecoder = NimbusJwtDecoder
                .withSecretKey(new SecretKeySpec(secretKey.getBytes(), "HmacSHA512"))
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
        try {
            // Giải mã Refresh Token
            return jwtDecoder.decode(refreshToken);
        } catch (JwtException e) {
            throw new JwtException("Invalid or expired refresh token");
        }
    }
}