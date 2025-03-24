package org.example.travelappproject.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.example.travelappproject.entity.Role;
import org.example.travelappproject.entity.User;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

@Service
public class JwtService {
    public String  generateToken(User user) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("id", user.getId());
        claims.put("username", user.getEmail());
        StringJoiner sj = new StringJoiner(",");
        for (Role role : user.getRoles()) {
            sj.add(role.getRoleName().toString());
        }
        claims.put("roles", sj.toString());
        return "Bearer "+ Jwts.builder()
                .subject(user.getEmail())
                .signWith(getSecretKey())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24*10))
                .claims(claims)
                .compact();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor("1234567891011121314151617181920212223242526272829303132".getBytes());
    }

    public boolean validate(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public String getUsername(String token) {
        Claims cl = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return cl.getSubject();
    }
}
