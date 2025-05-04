package com.codify.Auth_server.Filter;

import com.codify.Auth_server.Service.AuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class JwtAuthenticationFilter {
//    private final AuthenticationService authenticationService;
//
//    public JwtAuthenticationFilter(AuthenticationService authenticationService) {
//        this.authenticationService = authenticationService;
//    }
//
//    @Override
//
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String token=extractToken(request);
//        if (token!=null){
//           UserDetails userDetails= authenticationService.validateToken(token);
//            new UsernamePasswordAuthenticationToken(userDetails.getPassword(),userDetails.getUsername());
//        }
//
//
//    }
//    private String extractToken(HttpServletRequest request){
//        String bearerToken=request.getHeader("Authorization");
//        if(bearerToken != null && bearerToken.startsWith("bearer ")){
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
}
