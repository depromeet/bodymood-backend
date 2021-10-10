package com.depromeet.dgdg.external.kakao.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor
public class KakaoUserResponse {

    private String id;

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

    public String getNickName() {
        return this.properties.getNickname();
    }

    public String getProfileImage() {
        return this.properties.getProfileImage();
    }

}
