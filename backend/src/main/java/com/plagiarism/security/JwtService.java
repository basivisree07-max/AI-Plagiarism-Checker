package com.plagiarism.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    // Secret key used to sign and verify JWT tokens
    // Keep this private in a real project.
    @Value("${app.jwt.secret}")
    private String secretKey;

    // Token validity = 1 hour
    private static final long EXPIRATION_TIME =
            1000 * 60 * 60;

    // =========================
    // GET SIGNING KEY
    // =========================

    private SecretKey getSigningKey() {

        return Keys.hmacShaKeyFor(
                secretKey.getBytes(StandardCharsets.UTF_8)
        );
    }


    // =========================
    // GENERATE JWT TOKEN
    // =========================

    public String generateToken(String email, String role) {

        return Jwts.builder()

                // Store user's email
                .subject(email)

                // Store user's role
                .claim("role", role)

                // Token creation time
                .issuedAt(new Date())

                // Token expiration time
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + EXPIRATION_TIME
                        )
                )

                // Sign the token
                .signWith(getSigningKey())

                // Create final JWT string
                .compact();
    }


    // =========================
    // EXTRACT EMAIL
    // =========================

    public String extractEmail(String token) {

        Claims claims = Jwts.parser()

                .verifyWith(getSigningKey())

                .build()

                .parseSignedClaims(token)

                .getPayload();

        return claims.getSubject();
    }


    // =========================
    // EXTRACT ROLE
    // =========================

    public String extractRole(String token) {

        Claims claims = Jwts.parser()

                .verifyWith(getSigningKey())

                .build()

                .parseSignedClaims(token)

                .getPayload();

        return claims.get("role", String.class);
    }


    // =========================
    // CHECK TOKEN VALIDITY
    // =========================

    public boolean isTokenValid(String token, String email) {

        try {

            String tokenEmail = extractEmail(token);

            return tokenEmail.equals(email)
                    && !isTokenExpired(token);

        } catch (Exception e) {

            return false;
        }
    }


    // =========================
    // CHECK TOKEN EXPIRATION
    // =========================

    private boolean isTokenExpired(String token) {

        Claims claims = Jwts.parser()

                .verifyWith(getSigningKey())

                .build()

                .parseSignedClaims(token)

                .getPayload();

        return claims.getExpiration()
                .before(new Date());
    }
}
