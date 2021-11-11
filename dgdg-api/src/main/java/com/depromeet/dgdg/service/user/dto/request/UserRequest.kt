package com.depromeet.dgdg.service.user.dto.request

import javax.validation.constraints.NotBlank

data class UpdateUserInfoRequest(
    @field:NotBlank
    val name: String = "",
    val profileUrl: String?
)
