package com.products.products.util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    // 🔐 Secret Key (Base64 encoded recommended)
    private static final String SECRET_KEY = "mysecretkeymysecretkeymysecretkey123456";

    // ⏳ Token validity (15 min)
    private static final long JWT_EXPIRATION = 1000 * 60 * 15;

    // 🔑 Get signing key
    private static SecretKey getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // 🚀 Generate Token
    public static String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(getSigningKey())
                .compact();
    }

    // 📤 Extract Username
    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 📤 Extract Expiration
    public static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // 🧩 Extract Claims
    public static <T> T extractClaim(String token, Function<Claims, T> resolver) {
        final Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    // 🔍 Extract All Claims
    private static Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // ✅ Validate Token
    public boolean isTokenValid(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    // ⛔ Check Expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}