package com.depromeet.dgdg.config.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
@Getter
public class WebClientKakaoCaller {

    private final WebClient webClient;

//    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private final String userInfoUri;


    public KakaoProfileResponse getUserInfo(String accessToken) {
        return webClient.get()
            .uri(userInfoUri)
            .headers(headers -> headers.setBearerAuth(accessToken))
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(RuntimeException::new))
            .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(RuntimeException::new))
            .bodyToMono(KakaoProfileResponse.class)
            .block();

    }
}
