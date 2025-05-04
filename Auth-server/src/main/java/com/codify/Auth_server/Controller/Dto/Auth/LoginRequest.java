package com.codify.Auth_server.Controller.Dto.Auth;

import lombok.Getter;
import lombok.Setter;


public record LoginRequest(String email,String password) {
}
