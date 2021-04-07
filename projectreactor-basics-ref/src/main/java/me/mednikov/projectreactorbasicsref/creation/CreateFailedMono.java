package me.mednikov.projectreactorbasicsref.creation;

import reactor.core.publisher.Mono;

public class CreateFailedMono {

    public static void main(String[] args) {
        Mono<String> mono = Mono.error(new NoSuchFieldException());
        String name = mono.onErrorReturn("Barbora").block();
        System.out.println(name);
    }
}
