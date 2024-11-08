//package com.javaweb.buildingproject.utils;
//
//import com.nimbusds.jose.*;
//import com.nimbusds.jose.crypto.MACSigner;
//import com.nimbusds.jwt.JWTClaimsSet;
//import lombok.experimental.NonFinal;
//import lombok.extern.slf4j.Slf4j;
//
//import java.nio.charset.StandardCharsets;
//import java.time.Instant;
//import java.time.temporal.ChronoUnit;
//import java.util.Date;
//
//@Slf4j
//public class Token {
//
//    @NonFinal
//    public final String sign_key = "DYX1l3zdWdiOMwlu/qpzUqdyFrYtZEyjyeulLnOvR0yfWSk17vP6s+//XHNJO2Wd";
//
//    public String generateToken(String username){
//        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.ES512);
//        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
//                .subject(username)
//                .issuer("le nhat anh")
//                .issueTime(new Date())
//                .expirationTime(new Date(
//                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
//                ))
//                .claim("customeclaim","custom")
//                .build();
//        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
//        JWSObject jwsObject = new JWSObject(jwsHeader,payload);
//        try {
//            jwsObject.sign(new MACSigner(sign_key.getBytes()));
//            return jwsObject.serialize();
//        } catch (JOSEException e) {
//            log.error("cannot create token");
//            throw new RuntimeException(e);
//        }
//    }
//}
