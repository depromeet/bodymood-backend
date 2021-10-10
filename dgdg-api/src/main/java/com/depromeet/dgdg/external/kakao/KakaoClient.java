package com.depromeet.dgdg.external.kakao;

import com.depromeet.dgdg.external.kakao.dto.properties.KakaoProperties;
import com.depromeet.dgdg.external.kakao.dto.response.KakaoUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class KakaoClient {

    private final WebClient webClient;
    private final KakaoProperties kakaoProperties;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
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
