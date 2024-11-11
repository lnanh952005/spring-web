package com.javaweb.buildingproject.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class SecurityUtil {

    @Value("${hoidanit.jwt.base64-secret}")
    private String secretKey;
    @Value("${hoidanit.jwt.token-validity-in-seconds}")
    private long expiration;

    public String createToken(Authentication authentication) {
        try {
            String username = authentication.getName();

            JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
            JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                    .subject(username)
                    .issuer("lenhatanh.com")
                    .claim("lenhatanh",authentication)
                    .issueTime(new Date())
                    .expirationTime(Date.from(Instant.now().plus(expiration, ChronoUnit.SECONDS)))
                    .build();

            Payload payload = new Payload(jwtClaimsSet.toJSONObject());
            JWSObject jwsObject = new JWSObject(jwsHeader, payload);

            jwsObject.sign(new MACSigner(secretKey.getBytes()));
            return jwsObject.serialize();

        } catch (JOSEException e) {
            throw new RuntimeException("Error creating token: " + e.getMessage());
        }
    }
}
