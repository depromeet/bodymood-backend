package com.depromeet.dgdg.controller;

import com.depromeet.dgdg.config.kakaoLogin.AuthResponse;
import com.depromeet.dgdg.service.KakaoLoginService;
import com.depromeet.dgdg.config.kakaoLogin.LoginRequest;
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
    public BaseResponse<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        Long memberId = kakaoLoginService.login(request);
//       return BaseResponse.success(jwtAuthTokenProvider.createAccessToken(AuthTokenPayload.Companion.of(memberId)));
        return BaseResponse.success(AuthResponse.of(jwtAuthTokenProvider.createAccessToken(AuthTokenPayload.Companion.of(memberId))));

    }

}
