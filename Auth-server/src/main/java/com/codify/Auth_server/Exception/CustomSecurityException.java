package com.codify.Auth_server.Exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CustomSecurityException extends RuntimeException{
private final String message;
private final Integer code;
private final String status;

    public CustomSecurityException(String message, Integer code, String status) {
        this.message = message;
        this.code = code;
        this.status = status;
    }
}
