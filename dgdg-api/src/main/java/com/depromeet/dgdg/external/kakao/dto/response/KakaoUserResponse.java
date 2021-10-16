package com.depromeet.dgdg.external.kakao.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoUserResponse {

    private String id;

    private Properties properties;

    @ToString
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Properties {
        private String nickname;
        private String profileImage;
    }

    public String getNickName() {
        return this.properties.getNickname();
    }

    public String getProfileImage() {
        return this.properties.getProfileImage();
    }

}
