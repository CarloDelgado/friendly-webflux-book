package me.mednikov.webfluxjwt.models;

import lombok.Data;
import lombok.Value;

@Value
public class LoginResponse {

//    private booleanlean success;
    private String userId;
    private String token;

}
