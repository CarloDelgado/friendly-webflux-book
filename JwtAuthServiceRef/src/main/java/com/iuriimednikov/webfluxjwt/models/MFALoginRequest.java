package com.iuriimednikov.webfluxjwt.models;

import lombok.Value;

@Value
public class MFALoginRequest {
    
    String email;
    String password;
    String code;
}
