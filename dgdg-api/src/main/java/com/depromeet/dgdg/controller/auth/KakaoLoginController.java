package com.depromeet.dgdg.controller.auth;

import com.depromeet.dgdg.controller.dto.response.BaseResponse;
import com.depromeet.dgdg.service.auth.dto.response.AuthResponse;
import com.depromeet.dgdg.service.auth.KakaoLoginService;
import com.depromeet.dgdg.service.auth.dto.request.AuthRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class KakaoLoginController {

    private final KakaoLoginService kakaoLoginService;

    public KakaoLoginController(KakaoLoginService kakaoLoginService) {
        this.kakaoLoginService = kakaoLoginService;
    }

    @Operation(summary = "카카오 인증을 요청하는 PAI")
    @PostMapping("/api/v1/auth/kakao")
    public BaseResponse<AuthResponse> handleKakaoAuthentication(@Valid @RequestBody AuthRequest request) {
        return BaseResponse.success(kakaoLoginService.handleAuthentication(request));
    }

}
