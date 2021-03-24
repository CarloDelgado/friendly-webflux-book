package com.iuriimednikov.projectreactorbasicsref.subscription;

import reactor.core.publisher.Mono;

class Subscriptions {
    
    public static void main(String[] args) {
//        Mono<String> mono = Mono.just("Hello, world!");
        Mono<String> mono = Mono.error(new RuntimeException());
        String result = mono
                .doOnTerminate(() -> System.out.println("Mono was terminated"))
                .doOnError(t -> System.out.println("Error result"))
                .doOnSuccess(s -> System.out.println("Successful result!"))
                .block();
        System.out.println("Result is: " + result);
    }
    
}
