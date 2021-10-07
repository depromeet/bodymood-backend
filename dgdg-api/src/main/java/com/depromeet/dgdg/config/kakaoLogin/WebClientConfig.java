package com.depromeet.dgdg.config.kakaoLogin;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
            .exchangeStrategies(ExchangeStrategies.builder()
                .codecs(x -> x.defaultCodecs().maxInMemorySize(2 * 1024 * 1024))
                .build())
            .clientConnector(new ReactorClientHttpConnector(
                HttpClient.from(
                    TcpClient.create()
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                        .doOnConnected(conn -> conn.addHandler(new ReadTimeoutHandler(3000, TimeUnit.MILLISECONDS)))
                )
            )).build();

    }
}
