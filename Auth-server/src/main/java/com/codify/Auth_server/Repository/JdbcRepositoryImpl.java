package com.codify.Auth_server.Repository;

import com.codify.Auth_server.Controller.Dto.Auth.RegistrationDto;
import com.codify.Auth_server.Controller.Enum.RegistrationStatus;
import com.codify.Auth_server.Exception.CustomSecurityException;
import com.codify.Auth_server.Model.User;
import com.codify.Auth_server.Repository.Entity.UserTbl;
import com.codify.Auth_server.Repository.UserRepo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class JdbcRepositoryImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public JdbcRepositoryImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(RegistrationDto registrationDto) {
        log.info("registration request for user: {}", registrationDto.email());
        if (userRepository.existsByEmail(registrationDto.email())) {
            log.error("registration for email: {} failed due to exception: {}", registrationDto.email(), RegistrationStatus.ALREADY_REGISTERED.getValue());
            throw new CustomSecurityException("email already in use", 500, RegistrationStatus.ALREADY_REGISTERED.getValue());
        }
        UserTbl userTbl = UserTbl.builder()
                .email(registrationDto.email())
                .password(passwordEncoder.encode(registrationDto.password()))
                .build();
        UserTbl persistedUser = userRepository.save(userTbl);
        log.info("registration successful for user: {}", registrationDto.email());

        return persistedUser.toUser();
    }

    @Override
    public String getUserEmail(UUID id) {
        return userRepository.findEmailById(id);
    }
}
