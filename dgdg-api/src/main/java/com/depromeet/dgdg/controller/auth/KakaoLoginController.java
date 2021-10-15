package com.depromeet.dgdg.controller.auth;

import com.depromeet.dgdg.config.auth.AuthResponse;
import com.depromeet.dgdg.service.auth.KakaoLoginService;
import com.depromeet.dgdg.service.auth.dto.request.AuthRequest;
import com.depromeet.dgdg.controller.dto.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class KakaoLoginController {

    private final KakaoLoginService kakaoLoginService;

    @PostMapping("/api/v1/auth/kakao")
    public BaseResponse<AuthResponse> handleKakaoAuthentication(@Valid @RequestBody AuthRequest request) {
        return BaseResponse.success(kakaoLoginService.handleAuthentication(request));
    }

}
