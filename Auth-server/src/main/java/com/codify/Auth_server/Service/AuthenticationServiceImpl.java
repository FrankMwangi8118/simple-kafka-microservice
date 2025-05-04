package com.codify.Auth_server.Service;

import com.codify.Auth_server.Repository.Entity.UserTbl;
import com.codify.Auth_server.Repository.UserRepo.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service

public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }

    //    @Value("${jwt.secret}")
    private String secretKey = "qwertyuiolkjhgfrtyhhhhhh099f9f9f9f9j9j9j9j8h8hdi809j9j9jdjdjj9j9jd9d9jj9d9jd9jhhhhhhh";
    private final Long jwtExpiry = 86400000L;

    @Override
    public UserDetails authenticate(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        return userDetailsService.loadUserByUsername(email);
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        Optional<UserTbl> userTbl = userRepository.findByEmail(userDetails.getUsername());
        if (userTbl.isPresent()) {
            UserTbl userTbl1 = userTbl.get();
            claims.put("id", userTbl1.getEmail());
        }


        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiry);
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuer("pInGmYsErVer.io")
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    @Override
    public String getSubject(String token) {
        return getAllClaims(token).getSubject();
    }

    public Claims getAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


//    @Override
//    public UserDetails validateToken(String token) {
//        String username=extractSub(token);
//        return userDetailsService.loadUserByUsername(username);
//    }

//    private String extractSub(String token) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(getSigningKey())
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();
//        return claims.getSubject();
//    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = secretKey.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
