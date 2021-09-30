package com.depromeet.dgdg.service.token

interface AuthTokenProvider<T> {

    fun createAccessToken(payload: T): String

    fun getPayload(token: String): T

}
