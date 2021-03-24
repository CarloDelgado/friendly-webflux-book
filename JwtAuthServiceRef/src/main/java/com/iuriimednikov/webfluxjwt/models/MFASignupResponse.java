package com.iuriimednikov.webfluxjwt.models;

import lombok.Value;

@Value
public class MFASignupResponse {
    
    String userId;
    String secretKey;
}
