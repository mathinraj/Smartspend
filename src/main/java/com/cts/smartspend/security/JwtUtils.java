package com.cts.smartspend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.cts.smartspend.entity.User;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;
@Service
public class JwtUtils {
    // Expiration time for the JWT token set to 1 hour (in milliseconds)
    private static final long EXPIRATION_TIME_IN_MILLISEC = 1000L * 60L *60L;
    // Secret key used to sign the JWT token
    private SecretKey key;
    // JWT secret key fetched from application properties
    @Value("${secreteJwtString}")
    private String secreteJwtString;
    // Initialize the key after the bean is constructed (called after the constructor)
    @PostConstruct
    private void init(){
        // Convert the secret JWT string to a byte array and generate a key
        byte[] keyBytes = secreteJwtString.getBytes(StandardCharsets.UTF_8);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }
    // Method to generate JWT token using User entity
    public String generateToken(User user){
        String username = user.getUsername();
        return generateToken(username);
    }
    // Method to generate JWT token using a username string
    public String generateToken(String username){
        return Jwts.builder()
                .subject(username) // Set the subject (username) as the claim
                .issuedAt(new Date(System.currentTimeMillis())) // Set the issue date to current time
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MILLISEC)) // Set expiration time
                .signWith(key) // Sign the JWT token using the secret key
                .compact(); // Return the compact JWT token string
    }
    // Method to extract the username from the token
    public String getUsernameFromToken(String token){
        return extractClaims(token, Claims::getSubject);
    }
    // Private helper method to extract claims from the token
    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction){
        // Parse the token and extract the claims using the provided function
        return claimsTFunction.apply(Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload());
    }
    // Method to validate the JWT token
    public boolean isTokenValid(String token, UserDetails userDetails){
        // Extract username from token and compare with user details
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    // Private helper method to check if the token has expired
    private boolean isTokenExpired(String token){
        // Extract expiration claim and compare it with the current date
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }

}