//package com.javaweb.buildingproject.utils;
//
//import java.time.Instant;
//import java.time.temporal.ChronoUnit;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
//import org.springframework.security.oauth2.jwt.JwsHeader;
//import org.springframework.security.oauth2.jwt.JwtClaimsSet;
//import org.springframework.security.oauth2.jwt.JwtEncoder;
//import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SecurityUtil {
//
//    private final JwtEncoder jwtEncoder;
//
//    public SecurityUtil(JwtEncoder jwtEncoder) {
//        this.jwtEncoder = jwtEncoder;
//    }
//
//    public static final MacAlgorithm JWT_ALGORITHM = MacAlgorithm.HS512;
//
//    @Value("${hoidanit.jwt.token-validity-in-seconds}")
//    private long jwtExpiration;
//
//    public String createToken(Authentication authentication) {
//        Instant now = Instant.now();
//        Instant validity = now.plus(jwtExpiration, ChronoUnit.SECONDS);
//
//        JwtClaimsSet claims = JwtClaimsSet.builder()
//                .issuedAt(now)
//                .expiresAt(validity)
//                .subject(authentication.getName())
//                .claim("hoidanit", authentication.getAuthorities())
//                .build();
//
//        JwsHeader jwsHeader = JwsHeader.with(JWT_ALGORITHM).build();
//
//        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
//    }
//}
