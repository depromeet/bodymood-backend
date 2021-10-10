package com.depromeet.dgdg.controller.auth;

import com.depromeet.dgdg.config.auth.AuthResponse;
import com.depromeet.dgdg.service.auth.KakaoLoginService;
import com.depromeet.dgdg.service.auth.dto.request.AuthRequest;
import com.depromeet.dgdg.controller.dto.response.BaseResponse;
import com.depromeet.dgdg.provider.token.JwtAuthTokenProvider;
import com.depromeet.dgdg.provider.token.dto.AuthTokenPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class KakaoLoginController {

    private final KakaoLoginService kakaoLoginService;
    private final JwtAuthTokenProvider jwtAuthTokenProvider;

    @PostMapping("/api/v1/auth/kakao")
    public BaseResponse<AuthResponse> kakaoAuth(@Valid @RequestBody AuthRequest request) {
        Long memberId = kakaoLoginService.handleAuthentication(request);
        String token = jwtAuthTokenProvider.createAccessToken(AuthTokenPayload.of(memberId));
        return BaseResponse.success(AuthResponse.of(token));
    }

}
