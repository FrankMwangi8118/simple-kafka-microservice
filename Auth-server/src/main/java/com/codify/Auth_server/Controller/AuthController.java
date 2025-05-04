package com.codify.Auth_server.Controller;

import com.codify.Auth_server.Controller.Dto.ApiResponse;
import com.codify.Auth_server.Controller.Dto.Auth.RegistrationDto;
import com.codify.Auth_server.Controller.Enum.RegistrationStatus;
import com.codify.Auth_server.Model.User;
import com.codify.Auth_server.Repository.UserRepo.UserRepository;
import com.codify.Auth_server.Repository.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
@PostMapping("/register")
    public ResponseEntity<ApiResponse> registerClient(@RequestBody RegistrationDto registrationDto) {
        try {
            User registeredUser = userService.registerUser(registrationDto);
            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .statusCode(200)
                            .message("user register successfully")
                            .status(RegistrationStatus.SUCCESS.getValue())
                            .data(registeredUser)
                            .build()
            );

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    ApiResponse.builder()
                            .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                            .message(e.getMessage())
                            .build()
            );

        }
    }

}
