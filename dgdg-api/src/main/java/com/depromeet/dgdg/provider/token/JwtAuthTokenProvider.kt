package com.depromeet.dgdg.provider.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.exceptions.TokenExpiredException
import com.depromeet.dgdg.common.exception.JwtTokenExpiredException
import com.depromeet.dgdg.common.exception.UnAuthorizedException
import com.depromeet.dgdg.provider.token.dto.JwtProperties
import com.depromeet.dgdg.provider.token.dto.AuthTokenPayload
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtAuthTokenProvider(
    val jwtProperties: JwtProperties
) : AuthTokenProvider<AuthTokenPayload> {

    override fun createAccessToken(payload: AuthTokenPayload): String {
        try {
            val now = Date()
            val expiresAt = Date(now.time + jwtProperties.accessTokenExpiresTime)
            return JWT.create()
                .withIssuer(jwtProperties.issuer)
                .withClaim(USER_ID, payload.userId)
                .withIssuedAt(now)
                .withExpiresAt(expiresAt)
                .sign(Algorithm.HMAC256(jwtProperties.secret))
        } catch (exception: JWTCreationException) {
            throw IllegalArgumentException("액세스 토큰을 만드는 중 에러가 발생하였습니다 payload:($payload) message: (${exception.message})")
        } catch (exception: IllegalArgumentException) {
            throw IllegalArgumentException("액세스 토큰을 만드는 중 에러가 발생하였습니다 payload:($payload) message: (${exception.message})")
        }
    }

    override fun getPayload(accessToken: String): AuthTokenPayload {
        val verifier = JWT.require(Algorithm.HMAC256(jwtProperties.secret))
            .withIssuer(jwtProperties.issuer)
            .build()
        try {
            val jwt = verifier.verify(accessToken)
            return AuthTokenPayload.of(jwt.claims[USER_ID]?.asLong())
        } catch (exception: TokenExpiredException) {
            throw JwtTokenExpiredException("Access token($accessToken)이 만료되었습니다.")
        } catch (exception: JWTVerificationException) {
            throw UnAuthorizedException("Access token($accessToken)을 디코드 하는 중 에러가 발생하였습니다. message: ${exception.message}")
        }
    }

    override fun createRefreshToken(): String {
        try {
            val now = Date()
            val expiresAt = Date(now.time + jwtProperties.refreshTokenExpiresTime)
            return JWT.create()
                .withIssuer(jwtProperties.issuer)
                .withIssuedAt(now)
                .withExpiresAt(expiresAt)
                .sign(Algorithm.HMAC256(jwtProperties.secret))
        } catch (exception: JWTCreationException) {
            throw IllegalArgumentException("Refresh token을 만드는 중 에러가 발생하였습니다. message: (${exception.message})")
        } catch (exception: IllegalArgumentException) {
            throw IllegalArgumentException("Refresh token을 만드는 중 에러가 발생하였습니다. message: (${exception.message})")
        }
    }

    override fun validateRefreshToken(refreshToken: String) {
        val verifier = JWT.require(Algorithm.HMAC256(jwtProperties.secret))
            .withIssuer(jwtProperties.issuer)
            .build()
        try {
            verifier.verify(refreshToken)
        } catch (exception: TokenExpiredException) {
            throw JwtTokenExpiredException("token($refreshToken)이 만료되었습니다.")
        } catch (exception: JWTVerificationException) {
            throw UnAuthorizedException("token($refreshToken)을 디코드 하는 중 에러가 발생하였습니다. message: ${exception.message}")
        }
    }

    override fun isValidRefreshToken(refreshToken: String): Boolean {
        val verifier = JWT.require(Algorithm.HMAC256(jwtProperties.secret))
            .withIssuer(jwtProperties.issuer)
            .build()
        return try {
            verifier.verify(refreshToken)
            true
        } catch (exception: Exception) {
            false
        }
    }

    companion object {
        private const val USER_ID = "user_id"
    }

}
