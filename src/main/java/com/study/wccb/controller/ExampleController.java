package com.study.wccb.controller;

import com.study.wccb.model.Post;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.net.URI;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/example", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExampleController {

    private final WebClient webClient;


    @GetMapping
    public Mono<Post> getAll(){

        // "https://jsonplaceholder.typicode.com/posts/1"

        return webClient.get()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                .retrieve()
                .bodyToMono(Post.class);



    }
}
