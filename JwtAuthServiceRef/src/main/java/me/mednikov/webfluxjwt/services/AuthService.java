package me.mednikov.webfluxjwt.services;

import reactor.core.publisher.Mono;
import me.mednikov.webfluxjwt.models.LoginRequest;
import me.mednikov.webfluxjwt.models.LoginResponse;
import me.mednikov.webfluxjwt.models.MFALoginRequest;
import me.mednikov.webfluxjwt.models.MFASignupResponse;
import me.mednikov.webfluxjwt.models.SignupRequest;
import me.mednikov.webfluxjwt.models.SignupResponse;

public interface AuthService {

    Mono<SignupResponse> signup (SignupRequest request);

    Mono<LoginResponse> login (LoginRequest request);

    Mono<MFASignupResponse> signupMFA (SignupRequest request);

    Mono<LoginResponse> loginMFA (MFALoginRequest request);
    
    Mono<String> parseToken (String token);

}
