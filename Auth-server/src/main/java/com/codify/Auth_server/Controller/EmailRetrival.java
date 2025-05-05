package com.codify.Auth_server.Controller;

import com.codify.Auth_server.Repository.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/getUser")
public class EmailRetrival {
    private final UserService userService;

    public EmailRetrival(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/email/{id}")
    public String getUserId(@PathVariable UUID id) {
        return userService.getUserEmail(id);
    }
}
