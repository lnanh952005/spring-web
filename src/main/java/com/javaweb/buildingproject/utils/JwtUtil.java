//package com.javaweb.buildingproject.utils;
//
//import com.javaweb.buildingproject.entity.UserEntity;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class JwtUtil {
//
//    private String secretKey = "qoAEABDke07+AVLepXB4aCMtsT0wMAqR5x2VFyldsnx6e75YQkJH2UcZKTjEyoNgG71SBCXfq5N6NVZxWOfsHQ=="; // Thay thế bằng một khóa bảo mật mạnh
//
//    public String generateToken(UserEntity userEntity) {
//        return Jwts.builder()
//                .setSubject(userEntity.getUserName())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Hết hạn trong 1 ngày
//                .signWith(SignatureAlgorithm.HS256, secretKey)
//                .compact();
//    }
//
////    public boolean validateToken(String token) {
////        try {
////            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
////            return true;
////        } catch (Exception e) {
////            return false;
////        }
////    }
////
////    public String extractUsername(String token) {
////        return Jwts.parser().setSigningKey(secretKey)
////                .parseClaimsJws(token)
////                .getBody()
////                .getSubject();
////    }
//}
