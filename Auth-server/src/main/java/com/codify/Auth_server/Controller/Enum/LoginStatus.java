package com.codify.Auth_server.Controller.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public enum LoginStatus {
    LOGIN_FAILED("LOGIN ATTEMPT FAILED"),
    LOGIN_SUCCESSFUL("LOGIN ATTEMPT SUCCESSFUL");

    private final String value;

    LoginStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
