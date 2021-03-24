package com.iuriimednikov.webfluxjwt.services;

import com.iuriimednikov.webfluxjwt.models.LoginResponse;
import com.iuriimednikov.webfluxjwt.models.SignupResponse;
import com.iuriimednikov.webfluxjwt.models.LoginRequest;
import com.iuriimednikov.webfluxjwt.models.SignupRequest;
import com.iuriimednikov.webfluxjwt.models.User;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import com.iuriimednikov.webfluxjwt.errors.AlreadyExistsException;
import com.iuriimednikov.webfluxjwt.errors.LoginDeniedException;
import com.iuriimednikov.webfluxjwt.managers.TokenManager;
import com.iuriimednikov.webfluxjwt.managers.TotpManager;
import com.iuriimednikov.webfluxjwt.models.MFALoginRequest;
import com.iuriimednikov.webfluxjwt.models.MFASignupResponse;
import com.iuriimednikov.webfluxjwt.models.MFAUser;
import com.iuriimednikov.webfluxjwt.repositories.UserRepository;
import lombok.Value;

@Component("AuthService")
@Value
public class AuthServiceImpl implements AuthService{

    TokenManager tokenManager;
    TotpManager totpManager;
    UserRepository repository;

    @Override
    public Mono<SignupResponse> signup(SignupRequest request) {

        String email = request.getEmail().trim().toLowerCase();
        String password = request.getPassword();
        String salt = BCrypt.gensalt();
        String hash = BCrypt.hashpw(password, salt);
        String secret = totpManager.generateSecret();
        User user = new User(null, email, hash, salt);

        Mono<SignupResponse> response = repository.findByEmail(email)
                .defaultIfEmpty(user)
                .flatMap(result -> {
                    if (result.getUserId() == null) {
                        return repository.save(result).flatMap(result2 -> {
                            String userId = result2.getUserId();
                            SignupResponse signupResponse = new SignupResponse(userId);
                            return Mono.just(signupResponse);
                        });
                    } else {
                        return Mono.error(new AlreadyExistsException());
                    }
                });
        return response;
    }

    @Override
    public Mono<LoginResponse> login(LoginRequest request) {
        String email = request.getEmail().trim().toLowerCase();
        String password = request.getPassword();
        String code = request.getCode();
        Mono<LoginResponse> response = repository.findByEmail(email)
                .defaultIfEmpty(new User())
                .flatMap(user -> {
                    if (user.getUserId() == null) {
                        // no user
                        return Mono.empty();
                    } else {
                        // user exists
                        String salt = user.getSalt();
//                        String secret = user.getSecretKey();
                        boolean passwordMatch = BCrypt.hashpw(password, salt).equalsIgnoreCase(user.getHash());
                        if (passwordMatch) {
                            // password matched
//                            boolean codeMatched = totpManager.validateCode(code, secret);
//                            if (codeMatched) {
//                                String token = tokenManager.issueToken(user.getUserId());
//                                LoginResponse loginResponse = new LoginResponse();
//                                loginResponse.setToken(token);
//                                loginResponse.setUserId(user.getUserId());
//                                return Mono.just(loginResponse);
//                            } else {
//                                return Mono.error(new LoginDeniedException());
//                            }
                            String token = tokenManager.issueToken(user.getUserId());
                            LoginResponse loginResponse = new LoginResponse(user.getUserId(), token);
                            return Mono.just(loginResponse);
                        } else {
                            return Mono.error(new LoginDeniedException());
                        }
                    }
                });
        return response;
    }

    @Override
    public Mono<String> parseToken(String token) {
        return tokenManager.parse(token);
    }

    @Override
    public Mono<MFASignupResponse> signupMFA(SignupRequest request) {
            // generating a new user entity params
        String email = request.getEmail().trim().toLowerCase();
        String password = request.getPassword();
        String salt = BCrypt.gensalt();
        String hash = BCrypt.hashpw(password, salt);
        String secret = totpManager.generateSecret();

        MFAUser user = new MFAUser(null, email, hash, salt, secret);

        // preparing a Mono
        Mono<MFASignupResponse> response = repository.findByEmail(email)
                .defaultIfEmpty(new User()) // step 2
                .flatMap(result -> {
                    // assert, that user does not exist
                    if (result.getUserId() == null) {
                        return repository.save(result).flatMap(result2 -> {
                            // prepare token
                            String userId = result2.getUserId();
                            MFASignupResponse signupResponse = new MFASignupResponse(userId, secret);
                            return Mono.just(signupResponse);
                        });
                    } else {
                        // scenario - user already exists
                        return Mono.error(new RuntimeException());
                    }
                });
        return response;
    
    }

    @Override
    public Mono<LoginResponse> loginMFA(MFALoginRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
