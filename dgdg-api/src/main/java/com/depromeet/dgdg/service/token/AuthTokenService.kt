package com.depromeet.dgdg.service.token

interface AuthTokenService<T> {

    fun createAccessToken(payload: T): String

    fun getPayload(token: String): T

}
