package me.mednikov.webfluxjwt.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import me.mednikov.webfluxjwt.errors.LoginDeniedException;
import me.mednikov.webfluxjwt.models.LoginRequest;
import me.mednikov.webfluxjwt.models.LoginResponse;
import me.mednikov.webfluxjwt.models.MFALoginRequest;
import me.mednikov.webfluxjwt.models.MFASignupResponse;
import me.mednikov.webfluxjwt.models.SignupRequest;
import me.mednikov.webfluxjwt.models.SignupResponse;
import me.mednikov.webfluxjwt.services.AuthService;

@Component
@RequiredArgsConstructor
public class AuthHandler {

    private MediaType json = MediaType.APPLICATION_JSON;
    private final AuthService service;

    public Mono<ServerResponse> signup (ServerRequest request){
        Mono<SignupRequest> body = request.bodyToMono(SignupRequest.class);
        Mono<SignupResponse> result = body.flatMap(service::signup);
        return result.flatMap(data -> ServerResponse.ok().contentType(json).bodyValue(data))
                .onErrorResume(error -> ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> login (ServerRequest request){
        Mono<LoginRequest> body = request.bodyToMono(LoginRequest.class);
        Mono<LoginResponse> result = body.flatMap(service::login);
        return result.flatMap(data -> ServerResponse.ok().contentType(json).bodyValue(data))
                .switchIfEmpty(ServerResponse.notFound().build())
                .onErrorResume(error -> {
                    if (error instanceof LoginDeniedException){
                        return ServerResponse.badRequest().build();
                    }
                    return ServerResponse.status(500).build();
                });
    }

        public Mono<ServerResponse> signupMFA (ServerRequest request){
        Mono<SignupRequest> body = request.bodyToMono(SignupRequest.class);
        Mono<MFASignupResponse> result = body.flatMap(service::signupMFA);
        return result.flatMap(data -> ServerResponse.ok().contentType(json).bodyValue(data))
                .onErrorResume(error -> ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> loginMFA (ServerRequest request){
        Mono<MFALoginRequest> body = request.bodyToMono(MFALoginRequest.class);
        Mono<LoginResponse> result = body.flatMap(service::loginMFA);
        return result.flatMap(data -> ServerResponse.ok().contentType(json).bodyValue(data))
                .switchIfEmpty(ServerResponse.notFound().build())
                .onErrorResume(error -> {
                    if (error instanceof LoginDeniedException){
                        return ServerResponse.badRequest().build();
                    }
                    return ServerResponse.status(500).build();
                });
    }
}
