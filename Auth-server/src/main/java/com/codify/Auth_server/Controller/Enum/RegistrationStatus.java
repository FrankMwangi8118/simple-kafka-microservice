package com.codify.Auth_server.Controller.Enum;

public enum RegistrationStatus {
    SUCCESS("success"),
    ALREADY_REGISTERED("EMAIL ALREADY IN USE");
    private final String value;
    RegistrationStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public static RegistrationStatus customValueOf(String value){
        for(RegistrationStatus registrationStatus: values()){
            if(registrationStatus.getValue().equalsIgnoreCase(value)){
                return registrationStatus;
            }
        }
        throw new IllegalArgumentException(value + "not allowed");
    }
}
