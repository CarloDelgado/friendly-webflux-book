package me.mednikov.projectreactorbasicsref.mapping;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import reactor.core.publisher.Mono;

class ReactiveMap {
    
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://jsonplaceholder.typicode.com/todos/1"))
                .build();
        CompletableFuture<HttpResponse<String>> response = client
                .sendAsync(request, HttpResponse.BodyHandlers.ofString());
        
        Mono<HttpResponse<String>> mono = Mono.fromFuture(response);
        Mono<String> mapped = mono.map(r -> r.body());
        String body = mapped.block();
        System.out.println(body);
    }
    
}
