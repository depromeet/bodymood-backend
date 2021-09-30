package com.depromeet.dgdg.config.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final OAuth2Kakao oAuth2Kakao;

    public void oAuth2AuthorizationKakao(String code) throws JsonProcessingException {
        AuthorizationKakao authorization = oAuth2Kakao.getAccessToken(code);
        String userInfo = oAuth2Kakao.getUserInfo(authorization.getAccess_token());
        System.out.println("userInfo = " + userInfo);
    }
}
