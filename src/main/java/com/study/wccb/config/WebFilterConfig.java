package com.study.wccb.config;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class WebFilterConfig implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        HttpHeaders headers = exchange.getResponse().getHeaders();
        headers.add("web-filter", "web-filter-test");
        headers.add("developer", "Luiz Santos");
        headers.add("repository", "gitHub");
        headers.add("group", "study-plataform-azure");

        return chain.filter(exchange);
    }
}