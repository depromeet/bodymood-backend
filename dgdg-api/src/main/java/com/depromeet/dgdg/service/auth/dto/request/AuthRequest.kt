package com.depromeet.dgdg.service.auth.dto.request

import javax.validation.constraints.NotBlank

data class AuthRequest(
    @field:NotBlank(message = "accessToken을 입력해주세요")
    val accessToken: String
)

data class AppleAuthHeader(
    val kid: String,
    val alg: String
)
