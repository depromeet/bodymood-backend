package com.depromeet.dgdg.config.kakaoLogin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Getter
@Component
public class KakaoClient {
    private final WebClient webClient;
    private final KakaoProperties kakaoProperties;

    public KakaoUserResponse getUserInfo(String accessToken) {
        return webClient.get()
            .uri(kakaoProperties.getUserInfoUri())
            .headers(headers -> headers.setBearerAuth(accessToken))
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(RuntimeException::new))
            .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(RuntimeException::new))
            .bodyToMono(KakaoUserResponse.class)
            .block();
    }
}
