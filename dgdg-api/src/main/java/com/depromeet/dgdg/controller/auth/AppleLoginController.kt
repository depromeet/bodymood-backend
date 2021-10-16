package com.depromeet.dgdg.controller.auth

import com.depromeet.dgdg.service.auth.AppleLoginService
import com.depromeet.dgdg.service.auth.dto.request.AuthRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class AppleLoginController(
    val appleLoginService: AppleLoginService

) {

    @PostMapping("/api/v1/auth/apple")
    fun appleAuth(@RequestBody request: @Valid AuthRequest): String {
        val memberId: Long = appleLoginService.handleAuthentication(request)
//        val token: String = jwtAuthTokenProvider.createAccessToken(of(memberId))
        return "hello!"
    }
}

