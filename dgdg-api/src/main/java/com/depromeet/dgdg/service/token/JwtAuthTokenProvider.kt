package com.depromeet.dgdg.service.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.exceptions.TokenExpiredException
import com.depromeet.dgdg.common.exception.JwtTokenExpiredException
import com.depromeet.dgdg.common.exception.UnAuthorizedException
import com.depromeet.dgdg.service.token.dto.JwtProperties
import com.depromeet.dgdg.service.token.dto.AuthTokenPayload
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtAuthTokenProvider(
    val jwtProperties: JwtProperties
) : AuthTokenProvider<AuthTokenPayload> {

    override fun createAccessToken(payload: AuthTokenPayload): String {
        try {
            val now = Date()
            val expiresAt = Date(now.time + jwtProperties.expiresTime)
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

    override fun getPayload(token: String): AuthTokenPayload {
        val verifier = JWT.require(Algorithm.HMAC256(jwtProperties.secret))
            .withIssuer(jwtProperties.issuer)
            .build()
        try {
            val jwt = verifier.verify(token)
            return AuthTokenPayload.of(jwt.claims[USER_ID]?.asLong())
        } catch (exception: TokenExpiredException) {
            throw JwtTokenExpiredException("액세스 토큰($token)이 만료되었습니다.")
        } catch (exception: JWTVerificationException) {
            throw UnAuthorizedException("액세스 토큰($token)을 디코드 하는 중 에러가 발생하였습니다. message: ${exception.message}")
        }
    }

    companion object {
        private const val USER_ID = "user_id"
    }

}