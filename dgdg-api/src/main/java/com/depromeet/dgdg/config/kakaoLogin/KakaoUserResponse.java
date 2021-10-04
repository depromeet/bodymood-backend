package com.depromeet.dgdg.config.kakaoLogin;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class KakaoUserResponse {
    private String id;
    private String profileNickname;
    private String profileImage;
    private String accountEmail;


}
