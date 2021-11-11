package com.depromeet.dgdg.service.auth.dto.request

import javax.validation.constraints.NotBlank

data class AuthRequest(
    @field:NotBlank
    val accessToken: String = ""
)

data class AppleAuthHeader(
    val kid: String,
    val alg: String
)
