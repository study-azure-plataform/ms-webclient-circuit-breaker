package com.study.wccb.controller;

import com.study.wccb.model.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@Log
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/example", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExampleController {

    private final WebClient webClient;

    @GetMapping(path = "/users/{name}")
    public Mono<String> getName(@PathVariable String name) {
        return Mono.just(name);
    }

    @GetMapping("/{id}")
    public Mono<Post> getId(@PathVariable String id){

        // "https://jsonplaceholder.typicode.com/posts/1"

        log.info("### We wrap a single Employee resource in a Mono because we return at most one employee");
        Mono<Post> postMono = webClient.get()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                .retrieve()
                .bodyToMono(Post.class);

        //postMono.subscribe(System.out::println);
        return postMono;

    }

    @GetMapping
    public Flux<Post> getAll(){

        // "https://jsonplaceholder.typicode.com/posts"

        log.info("### For the collection resource, we use a Flux of type Post since that's the publisher for 0..n elements. ");

        Flux<Post> postFlux = webClient.get()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts" ))
                .retrieve()
                .bodyToFlux(Post.class);

        //postFlux.subscribe(System.out::println);
        return postFlux;
    }
}
