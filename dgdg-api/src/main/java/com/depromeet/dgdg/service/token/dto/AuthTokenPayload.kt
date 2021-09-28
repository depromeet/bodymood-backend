package com.depromeet.dgdg.service.token.dto

import com.depromeet.dgdg.common.exception.UnAuthorizedException

data class AuthTokenPayload(
    val userId: Long
) {

    companion object {
        fun of(userId: Long?): AuthTokenPayload {
            return AuthTokenPayload(userId ?: throw UnAuthorizedException("잘못된 토큰입니다. userId: $userId"))
        }
    }

}
