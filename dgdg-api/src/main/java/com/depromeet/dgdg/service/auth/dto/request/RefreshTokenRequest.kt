package com.depromeet.dgdg.service.auth.dto.request

import javax.validation.constraints.NotBlank

data class RefreshTokenRequest(
    @field:NotBlank
    val refreshToken: String = ""
)
