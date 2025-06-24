package edu.icet.security;

import edu.icet.dto.admin.Admin;
import edu.icet.dto.customer.Customer;
import edu.icet.dto.supplier.Supplier;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtUtil {

    private final SecretKey signingKey;
    private final long expirationTime;

    public JwtUtil(@Value("${jwt.secret}") String secret,
                   @Value("${jwt.expiration}") Long expirationTime) {
        if (secret == null || secret.getBytes().length < 32) {
            throw new IllegalArgumentException("JWT secret must be at least 256 bits (32 bytes)");
        }
        this.signingKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationTime = expirationTime;
    }

    public String generateToken (UserDetails userDetails, String role, Object entity) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);

        Long userID;
        String username;
        String profileImage;

        if (role.equals("CUSTOMER")) {
            Customer customer = (Customer) entity;

            username = customer.getFirstName() + " " + customer.getLastName();
            profileImage = customer.getProfileImageUrl();
            userID = customer.getId();

        } else if (role.equals("SUPPLIER")) {
            Supplier supplier = (Supplier) entity;

            username = supplier.getBusinessName();
            profileImage = supplier.getImageUrl();
            userID = supplier.getId();

        } else {
            Admin admin = (Admin) entity;

            username = admin.getEmail();
            profileImage = null;
            userID = admin.getId();
        }

        claims.put("user_id", userID);
        claims.put("username", username);
        claims.put("profile_image", profileImage);

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
            .signWith(signingKey, SignatureAlgorithm.HS256)
            .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
