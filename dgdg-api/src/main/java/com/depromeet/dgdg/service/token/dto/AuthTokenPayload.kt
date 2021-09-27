package com.depromeet.dgdg.service.token.dto

data class AuthTokenPayload(
    val memberId: Long
) {

    companion object {
        fun of(memberId: Long?): AuthTokenPayload {
            return AuthTokenPayload(memberId ?: throw IllegalArgumentException("잘못된 토큰입니다. memberId: $memberId"))
        }
    }

}
