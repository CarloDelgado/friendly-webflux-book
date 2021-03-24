package com.iuriimednikov.webfluxjwt.services;

import reactor.core.publisher.Mono;
import com.iuriimednikov.webfluxjwt.models.LoginRequest;
import com.iuriimednikov.webfluxjwt.models.LoginResponse;
import com.iuriimednikov.webfluxjwt.models.MFALoginRequest;
import com.iuriimednikov.webfluxjwt.models.MFASignupResponse;
import com.iuriimednikov.webfluxjwt.models.SignupRequest;
import com.iuriimednikov.webfluxjwt.models.SignupResponse;

public interface AuthService {

    Mono<SignupResponse> signup (SignupRequest request);

    Mono<LoginResponse> login (LoginRequest request);

    Mono<MFASignupResponse> signupMFA (SignupRequest request);

    Mono<LoginResponse> loginMFA (MFALoginRequest request);
    
    Mono<String> parseToken (String token);

}
