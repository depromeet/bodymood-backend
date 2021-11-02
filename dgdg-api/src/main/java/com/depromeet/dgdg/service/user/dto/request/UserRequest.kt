package com.depromeet.dgdg.service.user.dto.request

import javax.validation.constraints.NotBlank

data class UpdateUserInfoRequest(
    @field:NotBlank(message = "name을 입력해주세요")
    val name: String = "",
    val profileUrl: String?
)
