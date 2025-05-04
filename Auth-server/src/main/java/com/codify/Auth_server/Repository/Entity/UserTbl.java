package com.codify.Auth_server.Repository.Entity;

import com.codify.Auth_server.Model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTbl {
    @Id
    private UUID id;
    private String email;
    private String password;

    public User toUser(){
        return User.builder()
                .id(this.id)
                .email(this.email)
                .password(this.password)
                .build();
    }
    @PrePersist
    void prePersistId(){
        if(id==null){
            id=UUID.randomUUID();
        }
    }
}
