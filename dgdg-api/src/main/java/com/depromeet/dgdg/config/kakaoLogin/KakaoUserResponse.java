package com.depromeet.dgdg.config.kakaoLogin;


import lombok.*;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class KakaoUserResponse {

    private Long id;
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
