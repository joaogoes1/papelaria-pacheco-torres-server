package org.papelariapachecotorres.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import java.security.Key;

public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    private static final String SECRET = "papelaria-pacheco-torres-super-secreta-chave-para-jwt";
    private static final long EXPIRATION = 1000 * 60 * 60; // 1 hora
    private static final Key KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public static String validateToken(String token) {
        try {
            logger.info("Validating JWT token...");
            Jws<Claims> claims = Jwts.parser()
                    .verifyWith((javax.crypto.SecretKey) KEY)
                    .build()
                    .parseSignedClaims(token);
            String subject = claims.getPayload().getSubject();
            logger.info("JWT validation SUCCESS - Subject: {}", subject);
            return subject;
        } catch (JwtException e) {
            logger.error("JWT validation FAILED - JwtException: {}", e.getMessage());
            return null;
        } catch (IllegalArgumentException e) {
            logger.error("JWT validation FAILED - IllegalArgumentException: {}", e.getMessage());
            return null;
        } catch (Exception e) {
            logger.error("JWT validation FAILED - Unexpected error: {}", e.getMessage(), e);
            return null;
        }
    }
} 