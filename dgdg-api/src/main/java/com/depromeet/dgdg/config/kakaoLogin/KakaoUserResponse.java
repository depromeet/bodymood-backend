package com.depromeet.dgdg.config.kakaoLogin;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor
public class KakaoUserResponse {

    private Long id;

    private Properties properties;

    @ToString
    @Getter
    @NoArgsConstructor
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    private static class Properties {
        private String nickname;
        private String thumbnailImage;
        private String profileImage;
    }

}
