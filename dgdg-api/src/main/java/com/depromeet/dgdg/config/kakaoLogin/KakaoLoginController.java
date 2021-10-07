package com.depromeet.dgdg.config.kakaoLogin;

import com.depromeet.dgdg.controller.dto.response.BaseResponse;
import com.depromeet.dgdg.provider.token.JwtAuthTokenProvider;
import com.depromeet.dgdg.provider.token.dto.AuthTokenPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class KakaoLoginController {

    private final KakaoLoginService kakaoLoginService;
    private final JwtAuthTokenProvider jwtAuthTokenProvider;


    @PostMapping("/login")
    public BaseResponse<String> login(@Valid @RequestBody LoginRequest request) {
        Long memberId = kakaoLoginService.login(request);
        return BaseResponse.success(jwtAuthTokenProvider.createAccessToken(AuthTokenPayload.Companion.of(memberId)));
    }

}
