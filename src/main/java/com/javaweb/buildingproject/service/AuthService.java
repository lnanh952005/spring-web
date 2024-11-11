package com.javaweb.buildingproject.service;

import com.javaweb.buildingproject.domain.dto.LoginDTO;
import com.javaweb.buildingproject.domain.dto.RestLoginDTO;

import com.javaweb.buildingproject.exception.custom.NotFoundException;
import com.javaweb.buildingproject.utils.SecurityUtil;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final SecurityUtil securityUtil;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, SecurityUtil securityUtil) {
        this.authenticationManager = authenticationManager;
        this.securityUtil = securityUtil;
    }

    public RestLoginDTO login(LoginDTO loginDTO) {
        try{
            // Tạo đối tượng UsernamePasswordAuthenticationToken từ thông tin đăng nhập
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());

            // Thực hiện xác thực người dùng
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            // Tạo token từ thông tin xác thực đã được xác minh
            String token = securityUtil.createToken(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Tạo đối tượng RestLoginDTO và lưu trữ token
            RestLoginDTO restLoginDTO = new RestLoginDTO();
            restLoginDTO.setAccessToken(token);

            return restLoginDTO;
        }
        catch (Exception e){
            throw new NotFoundException("username or password is incorrect " + "(" + e.getMessage() + ")");
        }
    }
}