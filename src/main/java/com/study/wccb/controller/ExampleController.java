package com.study.wccb.controller;

import com.study.wccb.model.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@Log
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/example", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExampleController {

    private final WebClient webClientPlaceHolder;

   // @GetMapping(path = "/users/{name}")
    public Mono<ServerResponse> getName(@PathVariable String name) {
        String frase = "Olá " + name +"! Tudo bem ?";
        return ServerResponse.ok().body(frase, String.class);
    }

    @GetMapping("/{id}")
    public Mono<Post> getId(@PathVariable String id){
        log.info("### [MONO] Buscando um unico objeto");
        return webClientPlaceHolder
                .get().uri("/posts/{id}", id)
                .retrieve().bodyToMono(Post.class);

    }

    @GetMapping
    public Flux<Post> getAll(){
        log.info("### [FLUX] Buscando uma lista de objetos");
        return webClientPlaceHolder
                .get().uri("/posts" )
                .retrieve().bodyToFlux(Post.class);

    }
}
