package com.codify.Auth_server.Repository.UserRepo;

import com.codify.Auth_server.Repository.Entity.UserTbl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserTbl, UUID>{
    boolean existsByEmail(String email);
    Optional<UserTbl>findByEmail(String email);
}
