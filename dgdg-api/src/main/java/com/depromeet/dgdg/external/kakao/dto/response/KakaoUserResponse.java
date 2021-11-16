package com.depromeet.dgdg.external.kakao.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

public class KakaoUserResponse {

    private String id;

    private Properties properties;

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    private static class Properties {
        private String nickname;
        private String thumbnailImage;
        private String profileImage;

        public String getNickname() {
            return nickname;
        }

        public String getThumbnailImage() {
            return thumbnailImage;
        }

        public String getProfileImage() {
            return profileImage;
        }

    }

    public String getNickName() {
        return this.properties.getNickname();
    }

    public String getProfileImage() {
        return this.properties.getProfileImage();
    }

    public String getId() {
        return id;
    }

    public Properties getProperties() {
        return properties;
    }

}
