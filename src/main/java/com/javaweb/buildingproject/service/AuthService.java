package com.javaweb.buildingproject.service;

import com.javaweb.buildingproject.domain.dto.LoginDTO;
import com.javaweb.buildingproject.entity.UserEntity;
import com.javaweb.buildingproject.exception.custom.NotFoundException;
import com.javaweb.buildingproject.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;

import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private String secretKey = "qoAEABDke07+AVLepXB4aCMtsT0wMAqR5x2VFyldsnx6e75YQkJH2UcZKTjEyoNgG71SBCXfq5N6NVZxWOfsHQ==";

    public AuthService(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String authenticateUser(LoginDTO loginDTO) {
        Optional<UserEntity> userEntityOptional = userRepository.findByuserName(loginDTO.getUserName());
        if(userEntityOptional.isEmpty()) {
            throw new RuntimeException("Invalid username or password");
        }
        UserEntity userEntity = userEntityOptional.get();
        boolean authenticated = passwordEncoder.matches(loginDTO.getPassWord(), userEntity.getPassWord());
        if (authenticated) {
            throw new RuntimeException("Invalid username or password");
        }
        String token = generateToken(loginDTO.getUserName());
        return token;
    }

    public String generateToken(String username) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("lenhatanh.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try {
            jwsObject.sign(new MACSigner(secretKey.getBytes(StandardCharsets.UTF_8)));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}


//@Service
//public class AuthService {
//
//    private final AuthenticationManager authenticationManager;
//    private final SecurityUtil securityUtil;
//
//    public AuthService(AuthenticationManager authenticationManager, SecurityUtil securityUtil) {
//        this.authenticationManager = authenticationManager;
//        this.securityUtil = securityUtil;
//    }
//
//    public RestLoginDTO login(LoginDTO loginDTO) {
//        // Tạo một đối tượng UsernamePasswordAuthenticationToken từ thông tin đăng nhập
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(loginDTO.getUserName(), loginDTO.getPassWord());
//
//        // Thực hiện xác thực người dùng
//        Authentication authentication = authenticationManager.authenticate(authenticationToken);
//
//        // Tạo token từ thông tin xác thực đã được xác minh
//        String token = securityUtil.createToken(authentication);
//
//        // Tạo đối tượng RestLoginDTO và lưu trữ token
//        RestLoginDTO restLoginDTO = new RestLoginDTO();
//        restLoginDTO.setAccessToken(token);
//
//        return restLoginDTO;
//    }
//}
