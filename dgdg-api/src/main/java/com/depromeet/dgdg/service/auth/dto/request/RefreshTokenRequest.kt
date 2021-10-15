package com.depromeet.dgdg.service.auth.dto.request

import javax.validation.constraints.NotBlank

data class RefreshTokenRequest(
    @get:NotBlank(message = "refreshToken을 입력해주세요")
    val refreshToken: String
)
