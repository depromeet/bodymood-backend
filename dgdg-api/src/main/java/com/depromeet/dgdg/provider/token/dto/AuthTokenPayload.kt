package com.depromeet.dgdg.provider.token.dto

import com.depromeet.dgdg.common.exception.UnAuthorizedException

data class AuthTokenPayload(
    val userId: Long
) {

    companion object {
        @JvmStatic
        fun of(userId: Long?): AuthTokenPayload {
            return AuthTokenPayload(userId ?: throw UnAuthorizedException("잘못된 토큰입니다. userId: $userId"))
        }
    }

}
