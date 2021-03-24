package com.iuriimednikov.projectreactorbasicsref.creation;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import reactor.core.publisher.Mono;

public class WrapWithMono {

    public static void main(String[] args) throws Exception{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://jsonplaceholder.typicode.com/todos/1"))
                .build();
        CompletableFuture<String> response = client
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApplyAsync(result -> result.body());
        
        Mono<String> mono = Mono.fromFuture(response);
        String body = mono.block();
        
        System.out.println(body);
    }
}
