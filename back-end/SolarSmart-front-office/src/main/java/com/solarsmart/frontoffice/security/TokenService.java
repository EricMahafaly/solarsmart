package com.solarsmart.frontoffice.security;

import com.solarsmart.frontoffice.models.entities.User;
import com.solarsmart.frontoffice.models.exception.DomainException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class TokenService {

    @Value("${token.secret.key}")
    String jwtSecretKey;

//    @Value("${token.expirationms}")
    Long jwtExpirationMs;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String getTokenFromHeader(String header){
        String token = header.split(" ")[1].trim();

        return token;
    }

    public String extractId(String token){
        return extractClaim(token, Claims::getId);
    }

    public String generateToken(User authentication) {
        return Jwts
                .builder()
                .setId(authentication.getId().toString())
                .setSubject(authentication.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .claim("roles",authentication.getRoles())
                .compact();
    }

    public Claims extractAllClaims(String token) {
        try {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception exception){
            throw new DomainException("Token invalid!");
        }
    }

    public String getEmail(String token) {
        return this.extractAllClaims(token).getSubject();
    }

    public String[] getRoles(String token) {
        return ((List<String>) this.extractAllClaims(token).get("roles")).toArray(new String[]{});
    }

    private Key getSigningKey() {
        String secret = this.jwtSecretKey;
        if (secret == null) secret = "mNeJpeNKavHLK3MpQO4wordWA7Va8Rxk2XVa7diACgc=";
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }

    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, User userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getEmail()));
    }

}
