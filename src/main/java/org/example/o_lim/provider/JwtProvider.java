//package org.example.o_lim.provider;
//
//import io.jsonwebtoken.JwtParser;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.SecretKey;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Set;
//
//@Component
//public class JwtProvider {
//
//    public static final String BEARER_FIX = "Bearer ";
//
//    public static final String CLAIM_ROLES = "roles";
//
//    private final SecretKey key;
//    private final long jwtExpirationMs;
//    private final int clockSkewSeconds;
//
//    private final JwtParser parser;
//
//    public JwtProvider(
//            @Value("{jwt.secret}") String secret,
//            @Value("{jwt.expiration}") long jwtExpirationMs,
//            @Value("{jwt.clock-skew-seconds}") int clockSkewSeconds
//    ) {
//        byte[] secretBytes = Decoders.BASE64.decode(secret);
//        if(secretBytes.length < 32) {
//            throw new IllegalArgumentException("jwt.secret은 256 비트 이상이어야 합니다.");
//        }
//
//        this.key = Keys.hmacShaKeyFor(secretBytes);
//        this.jwtExpirationMs = jwtExpirationMs;
//        this.clockSkewSeconds = clockSkewSeconds;
//
//        this.parser = Jwts.parser()
//                .verifyWith(this.key)
//                .build();
//    }
//    public String generateJwtToken(String userId, Set<String> roles) {
//        long now = System.currentTimeMillis();
//        Date iat = new Date(now);
//        Date exp = new Date(now + jwtExpirationMs);
//
//        List<String> roleList = (roles == null) ? List.of() : new ArrayList<>(roles);
//
//        return Jwts.builder()
//                .setSubject(userId)
//                .claim(CLAIM_ROLES, roleList)
//                .setIssuedAt(iat)
//                .setExpiration(exp)
//                .signWith(key)
//                .compact();
//    }
//
//}
