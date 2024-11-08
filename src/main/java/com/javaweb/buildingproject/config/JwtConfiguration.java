//package com.javaweb.buildingproject.config;
//
//import com.nimbusds.jose.jwk.source.ImmutableSecret;
//import com.nimbusds.jose.util.Base64;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.jwt.JwtEncoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
//
//import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;
//
//@Configuration
//public class JwtConfiguration {
//
//    @Value("${hoidanit.jwt.base64-secret}")
//    private String jwtKey;
//
//    @Bean
//    public JwtEncoder jwtEncoder() {
//        return new NimbusJwtEncoder(new ImmutableSecret<>(getSecretKey()));
//    }
////    @Bean
////    public SecretKey getSecretKey() {
////        byte[] keyBytes = Base64.from(jwtKey).decode();
////        return new SecretKeySpec(keyBytes, 0, keyBytes.length, SecurityUtil.JWT_ALGORITHM.getName());
////    }
//}
