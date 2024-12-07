package com.javaweb.buildingproject.utils;

import com.javaweb.buildingproject.domain.dto.RestLoginDTO;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.*;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class JwtUtils {

    @Value("${hoidanit.jwt.base64-secret}")
    private String secretKey;

    @Value("${hoidanit.jwt.access-token-validity-in-seconds}")
    private long accessTokenExpiration;

    @Value("${hoidanit.jwt.refresh-token-validity-in-seconds}")
    private long refreshTokenExpiration;

    // Tạo Access Token
    public String createAccessToken(String name,RestLoginDTO.Userlogin userlogin) throws JOSEException {
        // Tạo header với thuật toán HS256
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        List<String> authorityList = new ArrayList<>();
        authorityList.add("ROLE_USER_CREATE");
        authorityList.add("ROLE_USER_UPDATE");

        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject(name)
                .claim("user", userlogin)
                .claim("permissions", authorityList)
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plusSeconds(accessTokenExpiration).toEpochMilli()))
                .build();
        Payload payload = new Payload(claims.toJSONObject());
        byte[] secretKeyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyBytes, MacAlgorithm.HS512.toString());

        JWSObject jwsObject = new JWSObject(header,payload);
        jwsObject.sign(new MACSigner(secretKeySpec));
        return jwsObject.serialize();
    }

    // Tạo Refresh Token
    public String createRefreshToken(String name,RestLoginDTO.Userlogin userlogin) throws JOSEException {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject(name)
                .claim("user", userlogin)
                .issueTime(new Date()) // Thời gian phát hành
                .expirationTime(new Date(Instant.now().plusSeconds(refreshTokenExpiration).toEpochMilli()))
                .build();
        Payload payload = new Payload(claims.toJSONObject());
        byte[] secretKeyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyBytes, MacAlgorithm.HS512.toString());

        JWSObject jwsObject = new JWSObject(header,payload);
        jwsObject.sign(new MACSigner(secretKeySpec));
        return jwsObject.serialize();
    }
}
