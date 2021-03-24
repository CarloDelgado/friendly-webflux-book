package com.iuriimednikov.webfluxjwt.models;

import lombok.Data;

@Data
public class SignupRequest {

    private String email;
    private String password;

}
