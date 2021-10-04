package com.depromeet.dgdg.config.kakaoLogin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class KakaoLoginController {

    private KakaoLoginService kakaoLoginService;

    @GetMapping("/login")
    public String login(@RequestHeader(value = "access_token", required = false) String accessToken) {
        return kakaoLoginService.login(accessToken);
    }
}
