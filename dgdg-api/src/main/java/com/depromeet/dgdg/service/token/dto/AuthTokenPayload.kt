package com.depromeet.dgdg.service.token.dto

import com.depromeet.dgdg.common.exception.UnAuthorizedException

data class AuthTokenPayload(
    val memberId: Long
) {

    companion object {
        fun of(memberId: Long?): AuthTokenPayload {
            return AuthTokenPayload(memberId ?: throw UnAuthorizedException("잘못된 토큰입니다. memberId: $memberId"))
        }
    }

}
