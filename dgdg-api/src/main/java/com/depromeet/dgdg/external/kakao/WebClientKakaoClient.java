package com.depromeet.dgdg.external.kakao;

import com.depromeet.dgdg.common.ErrorCode;
import com.depromeet.dgdg.common.exception.BadRequestException;
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
public class WebClientKakaoClient implements KakaoClient {

    private final WebClient webClient;
    private final KakaoProperties kakaoProperties;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public KakaoUserResponse getUserInfo(String accessToken) {
        return webClient.get()
            .uri(kakaoProperties.getUserInfoUri())
            .headers(headers -> headers.setBearerAuth(accessToken))
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new BadRequestException(String.format("잘못된 토큰 (%s) 입니다", accessToken), ErrorCode.BAD_REQUEST_EXCEPTION)))
            .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new IllegalArgumentException(String.format("카카오 외부 API 연동 중 에러가 발생하였습니다 token: (%s)", accessToken))))
            .bodyToMono(KakaoUserResponse.class)
            .block();
    }

}
