package com.acm.jwt.config;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.xml.bind.DatatypeConverter;

import com.acm.app.user.client.domain.User;
import com.acm.service.activeprofile.ActiveProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY = 18000000;

    private final String secret = "CA024AFFB510011EB554F04721BFB4279D11B06A";

    @Autowired
    private ActiveProfile activeProfile;

    /**
     * Pulls the username (Subject Field) from the token
     * 
     * @param token - The token being inspected
     * @return String of the subject field
     */
    public String getIdFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * Pulls the expiration date from a given token
     * 
     * @param token - The token being inspected
     * @return A Date object
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * Get Specfic claims from a token based on the passed in resolver
     * 
     * @param <T>            - Object type
     * @param token          - Token to be inspected
     * @param claimsResolver - Claims resolver
     * @return The generic type passed in of the claims
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Pulls all the claims off of a given token
     * 
     * @param token - The token to inspect and pull the claims from
     * @return Claims object is returned
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * Checks if the given token is expired
     * 
     * @param token - Token to pull the expiration date from
     * @return Returns a boolean object of true, false, or null
     */
    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * Sets up the fields to be added to the token
     * 
     * @param user - User info to be added to the token
     * @return String of the new JWT token
     */
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("username", user.getUsername());
        claims.put("firstName", user.getFirstName());
        claims.put("lastName", user.getLastName());
        claims.put("email", user.getEmail());
        claims.put("webRole", user.getWebRole());
        claims.put("web", activeProfile.getWebUrl());
        claims.put("auth", activeProfile.getMicroserviceUrl());
        claims.put("apps", Arrays.asList("people", "classroom"));
        claims.put("schoolId", 1);
        return doGenerateToken(claims);
    }

    /**
     * Generate a token based on the given Claims and subject
     * 
     * @param claims  - The claims/fields to be added to the token
     * @param subject - The main subject to be added to the field
     * @return String of the generated JWT token
     */
    private String doGenerateToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * Decodes a JWT token and returns the claims
     *
     * @param token - a JWT token that needs decoded
     * @return the decoded token
     */
    public Claims decodeToken(String token) {
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secret)).parseClaimsJws(token).getBody();
    }
}
