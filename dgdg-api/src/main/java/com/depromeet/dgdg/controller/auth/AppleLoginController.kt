package com.depromeet.dgdg.controller.auth

import com.depromeet.dgdg.service.auth.dto.response.AuthResponse
import com.depromeet.dgdg.controller.dto.response.BaseResponse
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
    fun appleAuth(@RequestBody request: @Valid AuthRequest): BaseResponse<AuthResponse> {
        return BaseResponse.success(appleLoginService.handleAuthentication(request))
    }
}

