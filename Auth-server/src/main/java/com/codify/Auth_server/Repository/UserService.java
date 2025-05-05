package com.codify.Auth_server.Repository;

import com.codify.Auth_server.Controller.Dto.Auth.RegistrationDto;
import com.codify.Auth_server.Model.User;
import org.springframework.stereotype.Service;

import java.util.UUID;


public interface UserService {
    User registerUser(RegistrationDto registrationDto);
    String getUserEmail(UUID id);
}
