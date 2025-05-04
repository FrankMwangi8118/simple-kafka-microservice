package com.codify.Auth_server.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class User {
    private UUID id;
    private String email;
    private String password;
}
