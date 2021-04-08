package me.mednikov.webfluxjwt.models;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String password;
    private String code;

}
