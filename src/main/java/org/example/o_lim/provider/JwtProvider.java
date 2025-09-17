package org.example.o_lim.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;

@Component
public class JwtProvider {

    public static final String BEARER_FIX = "Bearer ";

    public static final String CLAIM_ROLES = "roles";

    private final SecretKey key;
    private final long jwtExpirationMs;
    private final int clockSkewSeconds;

    private final JwtParser parser;

    public JwtProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long jwtExpirationMs,
            @Value("${jwt.clock-skew-seconds}") int clockSkewSeconds
    ) {
        byte[] secretBytes = Decoders.BASE64.decode(secret);
        if(secretBytes.length < 32) {
            throw new IllegalArgumentException("jwt.secret은 256 비트 이상이어야 합니다.");
        }

        this.key = Keys.hmacShaKeyFor(secretBytes);
        this.jwtExpirationMs = jwtExpirationMs;
        this.clockSkewSeconds = clockSkewSeconds;

        this.parser = Jwts.parser()
                .verifyWith(this.key)
                .build();
    }
    public String generateJwtToken(String userId, Set<String> roles) {
        long now = System.currentTimeMillis();
        Date iat = new Date(now);
        Date exp = new Date(now + jwtExpirationMs);

        List<String> roleList = (roles == null) ? List.of() : new ArrayList<>(roles);

        return Jwts.builder()
                .setSubject(userId)
                .claim(CLAIM_ROLES, roleList)
                .setIssuedAt(iat)
                .setExpiration(exp)
                .signWith(key)
                .compact();
    }

    public static String removeBearer(String bearerToken) {
        if(bearerToken == null || !bearerToken.startsWith(BEARER_FIX)) {
            throw new IllegalArgumentException("Authorization 형식이 올바르지 않습니다.");
        }
        return bearerToken.substring(BEARER_FIX.length()).trim();
    }

    private Claims parseClaimsInternal(String token, boolean allowClockSkewOnExpiry) {
        try {
            return parser.parseSignedClaims(token).getPayload();
        } catch (ExpiredJwtException e) {
            if(allowClockSkewOnExpiry && clockSkewSeconds > 0 && e.getClaims() != null) {
                Date exp = e.getClaims().getExpiration();
                if(exp != null) {
                    long skewMs = clockSkewSeconds * 1000L;
                    long now = System.currentTimeMillis();
                    if(now - exp.getTime() <= skewMs) {
                        return e.getClaims();
                    }
                }
            }
            throw e;
        }
    }
    public boolean isValidToken(String tokenWithoutBearer) {
        try{
            parseClaimsInternal(tokenWithoutBearer, true);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Claims getClaims(String tokenWithoutBearer) {
       return parseClaimsInternal(tokenWithoutBearer, true);
    }

    public String getUsernameFromJwt(String tokenWithoutBearer) {
        return getClaims(tokenWithoutBearer).getSubject();
    }

    public Set<String> getRolesFromJwt(String tokenWithoutBearer) {
        Object raw = getClaims(tokenWithoutBearer).get(CLAIM_ROLES);
        if (raw == null) return Set.of();

        if(raw instanceof List<?> list) {
            Set<String> result = new HashSet<>();
            for(Object o: list) if(o != null) result.add(o.toString());
            return result;
        }

        if(raw instanceof Set<?> set) {
            Set<String> result = new HashSet<>();
            for(Object o: set) if(o != null) result.add(o.toString());
            return result;
        }
        return Set.of(raw.toString());
    }

    public long getRemainingMillis(String tokenWithoutBearer) {
        Claims c = parseClaimsInternal(tokenWithoutBearer, true);
        return c.getExpiration().getTime() - System.currentTimeMillis();
    }
}
