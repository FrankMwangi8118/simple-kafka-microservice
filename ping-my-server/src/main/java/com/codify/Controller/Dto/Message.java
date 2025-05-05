package com.codify.Controller.Dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class message {
    private String ip;
    private String name;
    private String email;
}