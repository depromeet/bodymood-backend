package com.depromeet.dgdg.provider.token

interface AuthTokenProvider<T> {

    fun createAccessToken(payload: T): String

    fun getPayload(token: String): T

}
