package com.codify.Auth_server.Exception;

import com.codify.Auth_server.Controller.Dto.ApiResponse;
import com.codify.Auth_server.Controller.Dto.Auth.LoginRequest;
import com.codify.Auth_server.Controller.Enum.LoginStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
    @ExceptionHandler(CustomSecurityException.class)
    public ResponseEntity<ApiResponse> handleCustomException(CustomSecurityException customSecurityException) {
        return ResponseEntity.badRequest().body(
                ApiResponse.builder()
                        .message(customSecurityException.getMessage())
                        .statusCode(customSecurityException.getCode())
                        .status(customSecurityException.getStatus())
                        .build()
        );

    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse>handleBadCredentialsException(BadCredentialsException e){
        return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(
                ApiResponse.builder()
                        .statusCode(HttpStatus.UNAUTHORIZED.value())
                        .status(LoginStatus.LOGIN_FAILED.getValue())
                        .message("incorrect username or password")
                        .build()
        );
    }
}
