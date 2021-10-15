package com.depromeet.dgdg.controller.auth

import com.depromeet.dgdg.config.auth.RequiredAuth
import com.depromeet.dgdg.config.auth.UserId
import com.depromeet.dgdg.controller.dto.response.BaseResponse
import com.depromeet.dgdg.service.auth.AuthService
import com.depromeet.dgdg.service.auth.dto.request.RefreshTokenRequest
import com.depromeet.dgdg.service.auth.dto.response.RefreshTokenResponse
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class AuthController(
    private val authService: AuthService
) {

    @ApiOperation("로그아웃 API(Refresh Token 만료)")
    @RequiredAuth
    @PostMapping("/api/v1/logout")
    fun logout(@UserId userId: Long): BaseResponse<String> {
        authService.logout(userId)
        return BaseResponse.OK
    }

    @ApiOperation("액세스 토큰을 재발급 받는 API")
    @PostMapping("/api/v1/refresh/token")
    fun refreshAccessToken(
        @Valid @RequestBody request: RefreshTokenRequest
    ): BaseResponse<RefreshTokenResponse> {
        val accessToken = authService.refreshAccessToken(request)
        return BaseResponse.success(RefreshTokenResponse(accessToken))
    }

}
