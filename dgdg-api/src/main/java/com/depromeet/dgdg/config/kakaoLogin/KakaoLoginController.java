package com.depromeet.dgdg.config.kakaoLogin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class KakaoLoginController {

    private KakaoLoginService kakaoLoginService;

    @PostMapping("/login")
    public String login(@RequestHeader(value = "access_token", required = false) String accessToken) {
        return kakaoLoginService.login(accessToken);
    }
}
