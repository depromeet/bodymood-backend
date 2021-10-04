package com.depromeet.dgdg.config.auth.kakaoLogin;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.SslProvider;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
            .exchangeStrategies(ExchangeStrategies.builder()
                .codecs(x -> x.defaultCodecs().maxInMemorySize(2 * 1024 * 1024))
                .build())
            .clientConnector(new ReactorClientHttpConnector(
                HttpClient.create().compress(true).secure()))
            .
            ))


    }
}
