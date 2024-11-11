package com.javaweb.buildingproject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import javax.crypto.spec.SecretKeySpec;

@Configuration
public class JwtConfig {
    @Value("${hoidanit.jwt.base64-secret}")
    private String secretKey; // Khóa bí mật để giải mã JWT

//    @Bean
//    public JwtDecoder jwtDecoder() {
//        String jwkSetUri = "https://your-auth-server/.well-known/jwks.json"; // Đường dẫn JWKS của server xác thực
//        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
//    }

//    @Bean
//    public JwtDecoder jwtDecoder() {
//        byte[] secretKeyBytes = secretKey.getBytes();
//        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyBytes, "HmacSHA256");
//        return NimbusJwtDecoder.withSecretKey(secretKeySpec).build();
//    }
    @Bean
    public JwtDecoder jwtDecoder() {
        NimbusJwtDecoder nimbusJwtEncoder = NimbusJwtDecoder
                .withSecretKey(new SecretKeySpec(secretKey.getBytes(), "HmacSHA256"))
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
        // hoặc return về nimbusJwtEncoder
        return token -> {
            try{
                return nimbusJwtEncoder.decode(token);
            }
            catch(JwtException e){
                System.out.println("JWT Exception: " + e.getMessage());
                throw e;
            }
        };
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new
                JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthorityPrefix("");
        grantedAuthoritiesConverter.setAuthoritiesClaimName("lenhatanh");
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();

        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }
}
