package com.depromeet.dgdg.config.kakaoLogin;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor
public class KakaoUserResponse {

    private Long id;

    // TODO fix me
    private Properties properties;

    @ToString
    @Getter
    @NoArgsConstructor
    private static class Properties {
        private String profileNickname;
        private String profileImage;
        private String accountEmail;
    }

}
