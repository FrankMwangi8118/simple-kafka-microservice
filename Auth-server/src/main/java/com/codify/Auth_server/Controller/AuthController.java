package com.codify.Auth_server.Controller;

import com.codify.Auth_server.Controller.Dto.ApiResponse;
import com.codify.Auth_server.Controller.Dto.Auth.LoginRequest;
import com.codify.Auth_server.Controller.Dto.Auth.RegistrationDto;
import com.codify.Auth_server.Controller.Enum.LoginStatus;
import com.codify.Auth_server.Controller.Enum.RegistrationStatus;
import com.codify.Auth_server.Model.User;
import com.codify.Auth_server.Repository.UserRepo.UserRepository;
import com.codify.Auth_server.Repository.UserService;
import com.codify.Auth_server.Service.AuthenticationService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    public AuthController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
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

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest loginRequest) {
        UserDetails userDetails = authenticationService.authenticate(
                loginRequest.email(),
                loginRequest.password()
        );
        String token = authenticationService.generateToken(userDetails);
        log.info(token);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .statusCode(HttpStatus.SC_OK)
                        .status(LoginStatus.LOGIN_SUCCESSFUL.getValue())
                        .token(token)
                        .build()
        );
    }

    @PostMapping("/validate")
    public ResponseEntity<ApiResponse> getClaims(@RequestParam String token) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message(authenticationService.getSubject(token))
                        .build()
        );
    }

}
