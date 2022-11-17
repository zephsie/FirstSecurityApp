package com.zephsie.securityNew.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.subject}")
    private String subject;

    public String generateToken(String username) {
        Date expiration = Date.from(Instant.now().plusMillis(this.expiration));

        return JWT.create()
                .withSubject(subject)
                .withIssuer(issuer)
                .withExpiresAt(expiration)
                .withClaim("username", username)
                .withIssuedAt(new Date())
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateAndGetClaim(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject(subject)
                .withIssuer(issuer)
                .build();

        DecodedJWT jwt = verifier.verify(token);

        return jwt.getClaim("username").asString();
    }
}