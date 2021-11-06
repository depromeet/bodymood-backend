package com.depromeet.dgdg.provider.token

interface AuthTokenProvider<T> {

    fun createAccessToken(payload: T): String

    fun getPayload(accessToken: String): T

    fun createRefreshToken(): String

    fun validateRefreshToken(refreshToken: String)

    fun isValidRefreshToken(refreshToken: String): Boolean

}
