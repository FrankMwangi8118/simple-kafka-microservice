package com.codify.Auth_server.Service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {
    UserDetails authenticate(String email,String password);
    String generateToken(UserDetails userDetails);
//    UserDetails validateToken(String token);
    String getSubject(String token);
}
