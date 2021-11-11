package com.depromeet.dgdg.controller.auth

import com.depromeet.dgdg.config.auth.RequiredAuth
import com.depromeet.dgdg.config.auth.UserId
import com.depromeet.dgdg.controller.dto.response.BaseResponse
import com.depromeet.dgdg.service.auth.AuthService
import com.depromeet.dgdg.service.auth.dto.request.RefreshTokenRequest
import com.depromeet.dgdg.service.auth.dto.response.AuthResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class AuthController(
    private val authService: AuthService
) {

    @Operation(summary = "로그아웃 API (RefreshToken 만료)", security = [SecurityRequirement(name = "BearerKey")])
    @RequiredAuth
    @PostMapping("/api/v1/logout")
    fun logout(
        @UserId userId: Long
    ): BaseResponse<String> {
        authService.logout(userId)
        return BaseResponse.OK
    }

    @Operation(summary = "액세스 토큰을 재발급 받는 API")
    @PostMapping("/api/v1/refresh/token")
    fun refreshAccessToken(
        @Valid @RequestBody request: RefreshTokenRequest
    ): BaseResponse<AuthResponse> {
        val accessToken = authService.refreshAccessToken(request)
        return BaseResponse.success(AuthResponse(accessToken, request.refreshToken))
    }

}
