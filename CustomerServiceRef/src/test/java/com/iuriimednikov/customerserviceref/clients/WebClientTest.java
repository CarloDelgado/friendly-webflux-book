package com.iuriimednikov.customerserviceref.clients;

import com.iuriimednikov.customerserviceref.models.PostModel;
import java.net.URI;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

class WebClientTest {
    
    private WebClient client;
    
    @BeforeEach
    void setup(){
        client = WebClient.create();
    }
    
    @Test
    void getOnePostRequestTest(){
        Mono<PostModel> result = client.get()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PostModel.class);
        PostModel post = result.block();
        Assertions.assertThat(post)
                .hasFieldOrPropertyWithValue("userId", 1);
    }
    
    @Test
    void getRequestDataTest(){
        Mono<ResponseEntity<PostModel>> result = client.get()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(PostModel.class);
        
        ResponseEntity<PostModel> response = result.block();
        
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.hasBody()).isTrue();
    }
    
    @Test
    void getPostsTest(){
        Flux<PostModel> result = client.get()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(PostModel.class);
        Iterable<PostModel> posts = result.toIterable();
        Assertions.assertThat(posts).hasSize(100);
    }
    
    @Test
    void postRequestTest(){
        PostModel payload = new PostModel("body", "title", 101, 101);
        Mono<PostModel> result = client.post()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(PostModel.class);
        PostModel post = result.block();
        Assertions.assertThat(post).isEqualTo(payload);
    }
}
