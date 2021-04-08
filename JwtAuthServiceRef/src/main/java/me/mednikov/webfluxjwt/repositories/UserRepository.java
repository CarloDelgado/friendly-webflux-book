package me.mednikov.webfluxjwt.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import me.mednikov.webfluxjwt.models.User;

public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Mono<User> findByEmail (String email);

}
