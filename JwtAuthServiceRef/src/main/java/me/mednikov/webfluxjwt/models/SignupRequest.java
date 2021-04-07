package me.mednikov.webfluxjwt.models;

import lombok.Data;

@Data
public class SignupRequest {

    private String email;
    private String password;

}
