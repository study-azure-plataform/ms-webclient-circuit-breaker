package com.study.wccb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClientPlaceHolder(){

        return WebClient
                .builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .exchangeStrategies(
                        ExchangeStrategies.builder().codecs(c->c.defaultCodecs().enableLoggingRequestDetails(true)).build()
                )
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
