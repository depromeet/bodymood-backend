package com.depromeet.dgdg.controller

import com.depromeet.dgdg.config.auth.RequiredAuth
import com.depromeet.dgdg.config.auth.UserId
import com.depromeet.dgdg.service.token.AuthTokenService
import com.depromeet.dgdg.service.token.dto.AuthTokenPayload
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LocalTestController(
    private val authTokenService: AuthTokenService<AuthTokenPayload>
) {

    @GetMapping("/test-token")
    fun getTestToken(): BaseResponse<String> {
        val payload = AuthTokenPayload(100L)
        return BaseResponse.success(authTokenService.createAccessToken(payload))
    }

    @RequiredAuth
    @GetMapping("/sample-auth")
    fun getTest(
        @UserId userId: Long
    ): BaseResponse<Long> {
        return BaseResponse.success(userId)
    }

}
