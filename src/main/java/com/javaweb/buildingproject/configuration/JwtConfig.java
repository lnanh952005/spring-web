package com.javaweb.buildingproject.configuration;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.PlainJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Configuration
public class JwtConfig{
    @Value("${hoidanit.jwt.base64-secret}")
    private String secretKey; // Khóa bí mật để giải mã JWT

//    @Bean
//    public JwtDecoder jwtDecoder() {
//        NimbusJwtDecoder nimbusJwtDecoder = NimbusJwtDecoder
//                .withSecretKey(new SecretKeySpec(secretKey.getBytes(), MacAlgorithm.HS512.toString()))
//                .macAlgorithm(MacAlgorithm.HS512) // Đặt thuật toán mã hóa
//                .build();
//
//        return token -> {
//            try {
//                return nimbusJwtDecoder.decode(token); // Giải mã JWT token
//            } catch (JwtException e) {
//                System.out.println("JWT Exception: " + e.getMessage());
//                throw e;
//            }
//        };
//    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder
                .withSecretKey(new SecretKeySpec(secretKey.getBytes(), MacAlgorithm.HS512.toString()))
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("permisstion");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

//    @Bean
//    public JwtEncoder jwtEncoder() {
//        return new NimbusJwtEncoder(new ImmutableSecret<>(getSecretKey()));
//    }

//    @Bean
//    public SecretKey getSecretKey() {
//        byte[] ketBytes = Base64.from(secretKey).decode();
//        return new SecretKeySpec(ketBytes,MacAlgorithm.HS512.toString());
//    }
}
