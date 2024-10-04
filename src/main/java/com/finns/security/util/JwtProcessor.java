package com.finns.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
@Slf4j
@Component
public class JwtProcessor { // 헬퍼클래스란?
    static private final long TOKEN_VALID_MILISECOND = 1000L * 60 * 5; // 5 분
    private String secretKey = "충분히 긴 임의의(랜덤한) 비밀키 문자열 배정asdfasdasdasdf배정asdfasdasdasdf배정asdfasdasdasdf배정asdfasdasdasdf배정asdfasdasdasdf배정asdfasdasdasdf배정asdfasdasdasdf배정asdfasdasdasdf배정asdfasdasdasdf배정asdfasdasdasdf배정asdfasdasdasdf배정asdfasdasdasdf ";
    private Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
// private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); -- 운영시 사용

    public String generateToken(String subject) {
        log.debug("Generating token for subject: " + subject);
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + TOKEN_VALID_MILISECOND))
                .signWith(key)
                .compact();
    }

    public String getUsername(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            log.error("Failed to parse token", e);
            throw new RuntimeException("Invalid token");
        }
    }
}
